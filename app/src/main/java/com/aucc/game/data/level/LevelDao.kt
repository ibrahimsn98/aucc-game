package com.aucc.game.data.level

import android.arch.paging.DataSource
import android.arch.persistence.room.*

@Dao
interface LevelDao {

    @get:Query("SELECT * FROM levels ORDER BY id DESC")
    val getAll: DataSource.Factory<Int, Level>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg levels: Level): LongArray

    @Update
    fun update(levels: Level)

    @Delete
    fun delete(levels: Level)
}