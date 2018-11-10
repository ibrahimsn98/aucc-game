package com.aucc.game.data.level

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil

@Entity(tableName = "levels")
data class Level(@ColumnInfo(name = "title") val title: String,
                 @ColumnInfo(name = "desc") val desc: String,
                 @ColumnInfo(name = "completed") val completed: Boolean,
                 @ColumnInfo(name = "question") val question: String,
                 @ColumnInfo(name = "answer") val answer: String,
                 @Ignore var steps: List<Step>) {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Level>() {
            override fun areItemsTheSame(oldItem: Level, newItem: Level): Boolean = oldItem.id== newItem.id

            override fun areContentsTheSame(oldItem: Level, newItem: Level): Boolean =
                            oldItem.id == newItem.id &&
                            oldItem.title == newItem.title &&
                            oldItem.desc == newItem.desc &&
                            oldItem.question == newItem.question &&
                            oldItem.answer == newItem.answer
        }
    }
}

