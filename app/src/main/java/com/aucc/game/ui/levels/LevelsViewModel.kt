package com.aucc.game.ui.levels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelRepository
import javax.inject.Inject

class LevelsViewModel @Inject constructor(levelRepository: LevelRepository) : ViewModel() {

    val levels: LiveData<PagedList<Level>> = LivePagedListBuilder(levelRepository.getAll, PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPrefetchDistance(10)
        .setPageSize(20).build())
        .build()


}