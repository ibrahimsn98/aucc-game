package com.aucc.game.data.database

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.aucc.game.data.quest.Quest
import com.aucc.game.data.quest.QuestDao

@Database(entities = [Quest::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questDao(): QuestDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(app: Application): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(app, AppDatabase::class.java, "sdaddzuccc")
                    .allowMainThreadQueries().build()
            }

            return INSTANCE as AppDatabase
        }
    }
}