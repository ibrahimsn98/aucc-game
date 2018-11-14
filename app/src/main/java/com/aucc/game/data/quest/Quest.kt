package com.aucc.game.data.quest

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "account_quests")
data class Quest(@PrimaryKey(autoGenerate = false) val id: String,
                 @ColumnInfo(name = "title") val title: String,
                 @ColumnInfo(name = "created_at") val createdAt: Long)