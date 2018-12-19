package com.aucc.game.ui.levels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.aucc.game.data.quest.QuestRepository
import com.aucc.game.rest.datasource.LevelDataSource
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.RestRepository
import com.aucc.game.util.MainThreadExecutor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LevelsViewModel @Inject constructor(restRepository: RestRepository, questRepository: QuestRepository) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()
    private val executor = MainThreadExecutor()

    private val dataSource = LevelDataSource(restRepository, disposable, object: LevelDataSource.DataSourceCallback {
            override fun loading(isLoading: Boolean) {

            }

            override fun onError(message: String) {

            }
        })

    val levels = MutableLiveData<PagedList<Level>>().apply {
        value = PagedList.Builder(dataSource, PagedList.Config.Builder().setPageSize(10)
            .setInitialLoadSizeHint(10).setEnablePlaceholders(true).build())
            .setFetchExecutor(executor).setNotifyExecutor(executor).build()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}