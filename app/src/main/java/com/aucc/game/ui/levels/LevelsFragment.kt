package com.aucc.game.ui.levels

import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentLevelsBinding
import com.aucc.game.ui.main.MainActivity

class LevelsFragment : BaseFragment<MainActivity, FragmentLevelsBinding>() {

    override fun layoutRes(): Int {
        return R.layout.fragment_levels
    }
}