package com.aucc.game.ui.levels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import android.util.Log

class LevelsViewModel @Inject constructor(firestore: FirebaseFirestore, levelRepository: LevelRepository) : ViewModel() {

    val levels: LiveData<PagedList<Level>> = LivePagedListBuilder(levelRepository.getAll, PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPrefetchDistance(10)
        .setPageSize(20).build())
        .build()

    val fLevels = firestore.collection("levels").orderBy("id")

    init {
        levelRepository.insert(
            Level("Excellent Question", "Lorem ipsum dolor sit amet, dignissim definiebas mediocritatem his in",
            false, "Lorem ipsum dolor sit amet, dignissim definiebas mediocritatem his in, posse ipsum inimicus mel ne, mutat adhuc prompta nam id. Cu usu unum velit quaeque.",
                "deneme"))

        refresh()
    }

    fun refresh() {
        fLevels.get().addOnSuccessListener { documentSnapshots ->
            for (doc in  documentSnapshots.documents)
                Log.d("###", doc.get("title").toString())
        }.addOnFailureListener { e -> Log.d("####", "EROR:" + e.message) }
    }
}