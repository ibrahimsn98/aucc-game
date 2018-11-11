package com.aucc.game.data.level

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.aucc.game.util.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Source

class LevelDataSource(private val collection: CollectionReference,
                      private val dataSourceCallback: DataSourceCallback) : PageKeyedDataSource<Int, Level>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Int, Level>) {

        collection.get(Source.CACHE).addOnSuccessListener { p ->
            callback.onResult(
                p.toObjects(Level::class.java),
                0,
                p.size(),
                null,
                2
            )
        }.addOnFailureListener { e ->
            Log.d(Constants.TAG, e.message)
            dataSourceCallback.onError("Error: ${e.message}")
        }
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Level>) {

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>,
                            callback: PageKeyedDataSource.LoadCallback<Int, Level>) {
    }

    interface DataSourceCallback {
        fun onError(message: String)
    }
}