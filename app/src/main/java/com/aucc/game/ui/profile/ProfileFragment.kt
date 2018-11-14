package com.aucc.game.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentProfileBinding
import com.aucc.game.ui.main.MainActivity
import javax.inject.Inject

class ProfileFragment : BaseFragment<MainActivity, FragmentProfileBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProfileViewModel

    override fun layoutRes(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(ProfileViewModel::class.java)

        viewModel.quests.observe(this, Observer { quests ->

        })
    }
}