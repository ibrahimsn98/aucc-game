package com.aucc.game.data.level

import android.support.v7.util.DiffUtil

data class Level(val id: String = "",
                 val title: String = "",
                 val desc: String = "",
                 val question: String = "",
                 val answer: String = "",
                 val steps: ArrayList<Map<String, *>>? = null) {

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

