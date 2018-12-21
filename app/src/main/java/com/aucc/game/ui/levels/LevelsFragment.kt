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
import com.aucc.game.databinding.FragmentLevelsBinding
import com.aucc.game.rest.model.Level
import com.aucc.game.ui.main.MainActivity
import com.aucc.game.util.PrefUtils
import kotlinx.android.synthetic.main.fragment_levels.*
import javax.inject.Inject

class LevelsFragment : BaseFragment<MainActivity, FragmentLevelsBinding>(), LevelsAdapter.AdapterCallback {

    @Inject lateinit var prefUtils: PrefUtils
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var levelsAdapter: LevelsAdapter
    private lateinit var viewModel: LevelsViewModel

    override fun layoutRes(): Int {
        return R.layout.fragment_levels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity, viewModelFactory).get(LevelsViewModel::class.java)

        levelsAdapter = LevelsAdapter(this)

        levels.layoutManager = LinearLayoutManager(activity)
        levels.adapter = levelsAdapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.levels.observe(this, Observer<PagedList<Level>> {
            if (it != null) {
                levelsAdapter.submitList(it)
                levelsAdapter.completedLevels = prefUtils.getCompletedLevelIds()
            }
        })

        viewModel.viewState.observe(this, Observer<LevelsViewModel.ViewState> {
            if (it != null) render(it)
        })
    }

    override fun onLevelClicked(level: Level) {
        activity.openLevel(level)
    }

    private fun render(viewState: LevelsViewModel.ViewState) {
        if (viewState.isError && viewState.error != null)
            activity.showMessage(viewState.error)
    }
}