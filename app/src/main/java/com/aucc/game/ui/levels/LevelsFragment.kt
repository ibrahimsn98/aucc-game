package com.aucc.game.ui.levels

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.FragmentLevelsBinding
import com.aucc.game.ui.main.MainActivity
import javax.inject.Inject

class LevelsFragment : BaseFragment<MainActivity, FragmentLevelsBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var levelsAdapter: LevelsAdapter
    private lateinit var viewModel: LevelsViewModel

    override fun layoutRes(): Int {
        return R.layout.fragment_levels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(LevelsViewModel::class.java)

        levelsAdapter = LevelsAdapter(object: LevelsAdapter.AdapterCallback {
            override fun onRequestClicked(level: Level) {

            }
        })

        binding.levels.layoutManager = LinearLayoutManager(activity)
        binding.levels.adapter = levelsAdapter

        viewModel.levels.observe(this, Observer<PagedList<Level>> {
            levels -> if (levels != null) levelsAdapter.submitList(levels)
        })
    }
}