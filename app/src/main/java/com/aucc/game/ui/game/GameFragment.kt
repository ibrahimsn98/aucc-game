package com.aucc.game.ui.game

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.FragmentGameBinding
import com.aucc.game.ui.main.MainActivity
import javax.inject.Inject

class GameFragment : BaseFragment<MainActivity, FragmentGameBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var terminalAdapter: TerminalAdapter
    private lateinit var viewModel: GameViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity.setBottomNavVisibility(false)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDetach() {
        super.onDetach()
        activity.setBottomNavVisibility(true)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(GameViewModel::class.java)

        terminalAdapter = TerminalAdapter()

        binding.nestedScrollView.isNestedScrollingEnabled = true
        binding.terminalHistory.isNestedScrollingEnabled = false

        binding.terminalHistory.layoutManager = LinearLayoutManager(activity)
        binding.terminalHistory.adapter = terminalAdapter

        binding.terminal.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if( -1 != s.toString().indexOf("\n") ){

                    val text = binding.terminal.text.toString().trim()

                    if (text != "") {
                        if (text == "clear") {
                            terminalAdapter.clearLines()
                        } else {
                            terminalAdapter.addLine(TerminalAdapter.TerminalLine(text, false))
                            terminalAdapter.addLine(TerminalAdapter.TerminalLine("$text: command not found!", true))
                        }
                    }

                    binding.terminal.text.clear()
                }
            }

        })

        viewModel.level.observe(this, Observer<Level> {
            level -> if (level != null) binding.level = level
        })
    }
}