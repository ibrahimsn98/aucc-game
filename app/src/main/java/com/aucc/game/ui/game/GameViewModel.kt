package com.aucc.game.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelRepository
import javax.inject.Inject

class GameViewModel @Inject constructor(levelRepository: LevelRepository) : ViewModel() {

    val level = MutableLiveData<Level>()


}