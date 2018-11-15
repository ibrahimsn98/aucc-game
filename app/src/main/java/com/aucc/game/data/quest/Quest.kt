package com.aucc.game.data.quest

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil

@Entity(tableName = "account_quests")
data class Quest(@PrimaryKey(autoGenerate = false) val id: String,
                 @ColumnInfo(name = "title") val title: String,
                 @ColumnInfo(name = "created_at") val createdAt: Long) {

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Quest>() {
            override fun areItemsTheSame(oldItem: Quest, newItem: Quest): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Quest, newItem: Quest): Boolean =
                oldItem.id == newItem.id && oldItem.title == newItem.title
        }
    }
}