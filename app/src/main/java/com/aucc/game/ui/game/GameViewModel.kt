package com.aucc.game.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.Step
import com.aucc.game.util.HashUtils
import com.aucc.game.util.PrefUtils
import javax.inject.Inject

class GameViewModel @Inject constructor(private val prefUtils: PrefUtils) : ViewModel() {

    val level = MutableLiveData<Level>()
    val status = MutableLiveData<Boolean>()

    fun initialize(level: Level) {
        this.level.value = level
        status.value = null
    }

    fun checkAnswer(answer: String, step: Step) {
        status.postValue(HashUtils.md5(answer) == step.answer)
        Log.d("###", HashUtils.md5(answer) + " : " + step.answer)
    }

    fun setLevelCompleted(level: Level) {
        prefUtils.setLevelCompleted(level.id)
    }
}