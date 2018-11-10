package com.aucc.game.data.database

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.aucc.game.data.level.Level

/*@Database(entities = [], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

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
}*/