package com.aucc.game.data.level

import android.arch.paging.PageKeyedDataSource
import com.google.firebase.firestore.CollectionReference

class LevelDataSource(private val collection: CollectionReference,
                      private val dataSourceCallback: DataSourceCallback) : PageKeyedDataSource<Int, Level>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Int, Level>) {
        dataSourceCallback.loading(true)

        collection.get().addOnSuccessListener { p ->
            dataSourceCallback.loading(false)
            callback.onResult(
                p.toObjects(Level::class.java),
                0,
                p.size(),
                null,
                2
            )
        }.addOnFailureListener { e ->
            dataSourceCallback.loading(true)
            dataSourceCallback.onError("Error: ${e.message}")
        }
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Level>) {

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>,
                            callback: PageKeyedDataSource.LoadCallback<Int, Level>) {
    }

    interface DataSourceCallback {
        fun loading(isLoading: Boolean)
        fun onError(message: String)
    }
}