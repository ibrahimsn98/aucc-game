package com.aucc.game.rest.datasource

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.ListResponse
import com.aucc.game.rest.RestRepository
import com.aucc.game.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LevelDataSource(private val restRepository: RestRepository,
                      private val disposable: CompositeDisposable,
                      private val dataSourceCallback: DataSourceCallback) : PageKeyedDataSource<Int, Level>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Int, Level>) {
        dataSourceCallback.loading(true)

        disposable.add(restRepository.getLevels(1, params.requestedLoadSize).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableSingleObserver<ListResponse<Level>>() {
                override fun onSuccess(value: ListResponse<Level>) {
                    Log.d(Constants.TAG, "Retrofit success: Total records ${value.count}")
                    dataSourceCallback.loading(false)
                    callback.onResult(
                        value.results,
                        0,
                        value.count,
                        null,
                        2
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d(Constants.TAG, "Retrofit error: ${e.message}")
                    if (e.message != null) dataSourceCallback.onError(e.message!!)
                }
            })
        )
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Level>) {
        disposable.add(restRepository.getLevels(params.key, params.requestedLoadSize).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableSingleObserver<ListResponse<Level>>() {
                override fun onSuccess(value: ListResponse<Level>) {
                    Log.d(Constants.TAG, "Retrofit success: Total records ${value.count}")
                    callback.onResult(value.results, params.key + 1)
                }

                override fun onError(e: Throwable) {
                    Log.d(Constants.TAG, "Retrofit error: ${e.message}")
                    if (e.message != null) dataSourceCallback.onError(e.message!!)
                }
            })
        )
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>,
                            callback: PageKeyedDataSource.LoadCallback<Int, Level>) {
    }

    interface DataSourceCallback {
        fun loading(isLoading: Boolean)
        fun onError(message: String)
    }
}