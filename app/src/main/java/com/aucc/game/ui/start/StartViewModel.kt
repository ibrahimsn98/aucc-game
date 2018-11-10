package com.aucc.game.ui.start

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.aucc.game.data.level.Level
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StartViewModel @Inject constructor() : ViewModel()