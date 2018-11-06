package com.aucc.game.ui.game

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.FragmentGameBinding
import com.aucc.game.ui.main.MainActivity
import com.aucc.game.util.TermEditText
import javax.inject.Inject
import android.view.KeyEvent.KEYCODE_ENTER



class GameFragment : BaseFragment<MainActivity, FragmentGameBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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

        binding.terminal.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if( -1 != s.toString().indexOf("\n") ){
                    if (binding.terminal.text.toString() != "") {
                        if (binding.terminal.text.toString() == "clear\n") {
                            binding.terminalHistory.text = "Shell initialized!"
                        } else {
                            binding.terminalHistory.append(Html.fromHtml("<br><font color='#9acc14'>root@aucc:~/$</font> ${binding.terminal.text}"))
                            binding.terminalHistory.append("\nCommand not found: ${binding.terminal.text}")
                        }
                    }

                    binding.terminal.text.clear()
                }
            }

        })

        /*binding.terminal.setOnKeyListener { _, keyCode, event ->

            Log.d("###", "dene" + keyCode.toString())

            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if (binding.terminal.text.toString() != "") {
                    if (binding.terminal.text.toString() == "clear") {
                        binding.terminalHistory.text = "Shell initialized!"
                    }else {
                        binding.terminalHistory.append(Html.fromHtml("<br><font color='#9acc14'>root@aucc:~/$</font> ${binding.terminal.text}"))
                        binding.terminalHistory.append("\nCommand not found: ${binding.terminal.text}")
                    }
                }

                binding.terminal.text.clear()

                true
            } else false
        }*/

        viewModel.level.observe(this, Observer<Level> {
            level -> if (level != null) binding.level = level
        })
    }
}