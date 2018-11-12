package com.aucc.game.ui.game

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.FragmentGameBinding
import com.aucc.game.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_game_step.*
import kotlinx.android.synthetic.main.dialog_game_step.view.*
import javax.inject.Inject

class GameFragment : BaseFragment<MainActivity, FragmentGameBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var terminalAdapter: TerminalAdapter
    private lateinit var viewModel: GameViewModel

    private lateinit var level: Level
    private var step = 0

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
                            checkAnswer(text)
                        }
                    }

                    binding.terminal.text.clear()
                }
            }

        })

        viewModel.level.observe(this, Observer<Level> { level ->
            if (level != null) {
                this.level = level
                binding.level = level
            }
        })
    }

    private fun checkAnswer(answer: String) {
        val step = level.steps!![step]

        if (answer == step["expected_answer"].toString()) {
            terminalAdapter.addLine(TerminalAdapter.TerminalLine(step["true_answer"].toString(), true))
            showMessage(step["desc"].toString())
        }else {
            terminalAdapter.addLine(TerminalAdapter.TerminalLine("$answer: command not found!", true))
        }
    }

    @SuppressLint("InflateParams")
    private fun showMessage(msg: String): Snackbar {
        val snackbar = Snackbar.make(activity.coordinator, msg, Snackbar.LENGTH_INDEFINITE)
        val textView = snackbar.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        val button = snackbar.view.findViewById(android.support.design.R.id.snackbar_action) as Button

        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_hacker, 0, 0, 0)
        textView.compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.snackbar_icon_padding)
        textView.textSize = 16F
        textView.maxLines = 5

        button.textSize = 17F

        snackbar.setAction("OK") { snackbar.dismiss() }
        snackbar.show()

        return snackbar
    }
}