package com.aucc.game.ui.profile

import android.os.Bundle
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentProfileBinding
import com.aucc.game.ui.main.MainActivity

class ProfileFragment : BaseFragment<MainActivity, FragmentProfileBinding>() {

    override fun layoutRes(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}