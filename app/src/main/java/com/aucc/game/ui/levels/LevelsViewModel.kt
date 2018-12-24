package com.aucc.game.ui.levels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.aucc.game.rest.datasource.LevelDataSource
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.RestRepository
import com.aucc.game.util.MainThreadExecutor
import com.aucc.game.util.PrefUtils
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LevelsViewModel @Inject constructor(prefUtils: PrefUtils, restRepository: RestRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val executor = MainThreadExecutor()

    val levels = MutableLiveData<PagedList<Level>>()
    val viewState = MutableLiveData<ViewState>().apply {
        value = ViewState(isLoading = true)
    }

    private val dataSource = LevelDataSource(prefUtils, restRepository, disposable, object: LevelDataSource.DataSourceCallback {
            override fun loading(isLoading: Boolean) {
                viewState.postValue(viewState.value!!.copy(isLoading=isLoading))
            }

            override fun onError(message: String) {
                viewState.postValue(viewState.value!!.copy(isError = true, error = message))
            }
        })

    init {
        levels.value = PagedList.Builder(dataSource, PagedList.Config.Builder().setPageSize(20)
            .setEnablePlaceholders(true).build())
            .setFetchExecutor(executor).setNotifyExecutor(executor).build()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    data class ViewState(val isLoading: Boolean = false, val isError: Boolean = false, val error: String? = null)
}