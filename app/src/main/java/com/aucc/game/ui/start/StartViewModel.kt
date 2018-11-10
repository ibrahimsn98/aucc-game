package com.aucc.game.ui.start

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StartViewModel @Inject constructor(private val firestore: FirebaseFirestore, levelRepository: LevelRepository) : ViewModel() {

    private val levelCollection = firestore.collection("levels")

    val level : Level? = null


    init {
        levelCollection.get().addOnSuccessListener { documentSnapshots ->
            for (doc in  documentSnapshots.documents)
                Log.d("###", doc.get("title").toString())
        }.addOnFailureListener { e -> Log.d("####", "EROR:" + e.message) }
    }


}