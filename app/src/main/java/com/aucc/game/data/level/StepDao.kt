package com.aucc.game.data.level

import android.arch.persistence.room.*

@Dao
interface StepDao {

    @get:Query("SELECT * FROM steps ORDER BY id ASC")
    val getAll: List<Step>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg steps: Step): LongArray

    @Update
    fun update(steps: Step)

    @Delete
    fun delete(steps: Step)
}