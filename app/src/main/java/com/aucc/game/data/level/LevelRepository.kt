package com.aucc.game.data.level

import android.arch.paging.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelRepository @Inject constructor(private val levelDao: LevelDao) {

    val getAll: DataSource.Factory<Int, Level> get() = levelDao.getAll

    fun insert(vararg levels: Level): Long {
        return levelDao.insert(*levels)[0]
    }

    fun update(level: Level) {
        levelDao.update(level)
    }

    fun delete(level: Level) {
        levelDao.delete(level)
    }
}