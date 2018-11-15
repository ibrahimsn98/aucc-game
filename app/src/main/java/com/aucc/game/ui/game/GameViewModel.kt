package com.aucc.game.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aucc.game.data.level.Level
import com.aucc.game.data.quest.Quest
import com.aucc.game.data.quest.QuestRepository
import javax.inject.Inject

class GameViewModel @Inject constructor(private val questRepository: QuestRepository) : ViewModel() {

    val level = MutableLiveData<Level>()

    fun setLevelCompleted(level: Level) {
        questRepository.insert(Quest(level.id, level.title, System.currentTimeMillis()/1000))
    }

    fun isLevelCompleted(level: Level): Boolean {
        return questRepository.isExists(level.id)
    }
}