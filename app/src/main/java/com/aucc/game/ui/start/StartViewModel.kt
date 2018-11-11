package com.aucc.game.ui.start

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.aucc.game.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import javax.inject.Inject

class StartViewModel @Inject constructor(firestore: FirebaseFirestore) : ViewModel() {

    val status = MutableLiveData<Boolean>()

    private var levelDoc = firestore.collection("levels")

    init {
        levelDoc.get(Source.SERVER).addOnSuccessListener { p ->
            status.postValue(true)
        }.addOnFailureListener { e ->
            Log.d(Constants.TAG, e.message)
            status.postValue(false)
        }
    }

}