package com.aucc.game.data.database

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.aucc.game.data.level.Level
import com.aucc.game.data.level.LevelDao

@Database(entities = [Level::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun levelDao(): LevelDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(app: Application): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(app, AppDatabase::class.java, "aucc")
                    .allowMainThreadQueries().build()
            }

            return INSTANCE as AppDatabase
        }
    }
}