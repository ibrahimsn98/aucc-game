package com.aucc.game.data.quest

import android.arch.paging.DataSource
import android.arch.persistence.room.*

@Dao
interface QuestDao {

    @get:Query("SELECT * FROM account_quests")
    val getAll: DataSource.Factory<Int, Quest>

    @get:Query("SELECT id FROM account_quests")
    val getIdList: List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg quests: Quest): LongArray

    @Update
    fun update(quest: Quest)

    @Delete
    fun delete(quest: Quest)
}
