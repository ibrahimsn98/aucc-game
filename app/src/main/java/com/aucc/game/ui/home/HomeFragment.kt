package com.aucc.game.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentHomeBinding
import com.aucc.game.ui.main.MainActivity

class HomeFragment : BaseFragment<MainActivity, FragmentHomeBinding>() {

    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}