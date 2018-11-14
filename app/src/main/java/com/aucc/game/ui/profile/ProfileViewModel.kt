package com.aucc.game.ui.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.aucc.game.data.quest.Quest
import com.aucc.game.data.quest.QuestRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(questRepository: QuestRepository) : ViewModel() {

    val quests: LiveData<PagedList<Quest>> = LivePagedListBuilder(questRepository.getAll, PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPrefetchDistance(10)
        .setPageSize(20).build())
        .build()

}
