package com.aucc.game.ui.game

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.FragmentGameBinding
import com.aucc.game.ui.main.MainActivity
import javax.inject.Inject

class GameFragment : BaseFragment<MainActivity, FragmentGameBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GameViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity.setBottomNavVisibility(false)
    }

    override fun onDetach() {
        super.onDetach()
        activity.clearFragmentStack()
        activity.setBottomNavVisibility(true)
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(GameViewModel::class.java)

        viewModel.level.observe(this, Observer<Level> {
            level -> if (level != null) binding.level = level
        })
    }
}