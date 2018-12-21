package com.aucc.game.ui.game

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentGameBinding
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.StatusResponse
import com.aucc.game.ui.main.MainActivity
import javax.inject.Inject

class GameFragment : BaseFragment<MainActivity, FragmentGameBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GameViewModel
    private lateinit var level: Level

    private val bottomSheet = GameBottomSheet()
    private val terminalAdapter = TerminalAdapter()

    private var step = 0
    private var processing = false
    private var lastProcess = 0L

    override fun layoutRes(): Int {
        return R.layout.fragment_game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(GameViewModel::class.java)

        binding.terminalHistory.layoutManager = LinearLayoutManager(activity)
        binding.terminalHistory.adapter = terminalAdapter

        binding.terminal.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(-1 != s.toString().indexOf("\n") ){

                    val text = binding.terminal.text.toString().trim()
                    val time = System.currentTimeMillis()

                    if (text != "") {
                        if (text == "clear")
                            terminalAdapter.clearLines()
                        else if (!processing && time - lastProcess > 1000) {
                            lastProcess = time
                            addHistory(text, false)
                            viewModel.checkAnswer(level.steps[step].id, text)
                        }
                    }

                    binding.terminal.text.clear()
                }
            }

        })

        viewModel.level.observe(this, Observer<Level> {
            if (it != null) {
                level = it
                binding.level = it
            }
        })

        viewModel.processing.observe(this, Observer<Boolean> {
            processing = it ?: false
        })

        viewModel.status.observe(this, Observer<StatusResponse> {
            if (it != null)
                if (it.status) {
                    addHistory(level.steps[0].rightResponse, true)
                    bottomSheet.show(activity.supportFragmentManager, "bottom-sheet")
                } else
                    addHistory("Wrong move.. You can try again!", true)
        })

        viewModel.error.observe(this, Observer<String> {
            if (it != null) addHistory(it, true)
        })
    }

    private fun addHistory(text: String, isResponse: Boolean) {
        terminalAdapter.addLine(TerminalAdapter.TerminalLine(text, isResponse))
        binding.terminalHistory.scrollToPosition(terminalAdapter.itemCount - 1)
    }
}