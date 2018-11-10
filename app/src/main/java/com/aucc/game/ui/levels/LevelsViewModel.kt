package com.aucc.game.ui.levels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelDataSource
import com.aucc.game.util.MainThreadExecutor
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LevelsViewModel @Inject constructor(firestore: FirebaseFirestore) : ViewModel() {

    private val dataSource: LevelDataSource
    private val executor = MainThreadExecutor()

    private var levelDoc = firestore.collection("levels")
    val levels = MutableLiveData<PagedList<Level>>()

    init {
        dataSource = LevelDataSource(levelDoc, object: LevelDataSource.DataSourceCallback {
            override fun loading(isLoading: Boolean) {

            }

            override fun onError(message: String) {

            }
        })

        levels.value = PagedList.Builder(dataSource, PagedList.Config.Builder()
            .setPageSize(100).setEnablePlaceholders(true).build())
            .setFetchExecutor(executor).setNotifyExecutor(executor).build()
    }
}