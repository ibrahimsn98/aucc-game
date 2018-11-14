package com.aucc.game.data.quest

import android.arch.paging.DataSource
import javax.inject.Inject

class QuestRepository @Inject constructor(private val questDao: QuestDao) {

    val getAll: DataSource.Factory<Int, Quest> get() = questDao.getAll

    fun insert(vararg quests: Quest): Long {
        return questDao.insert(*quests)[0]
    }

    fun update(quest: Quest) {
        questDao.update(quest)
    }

    fun delete(quest: Quest) {
        questDao.delete(quest)
    }
}