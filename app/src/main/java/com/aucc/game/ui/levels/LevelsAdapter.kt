package com.aucc.game.ui.levels

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aucc.game.R
import com.aucc.game.databinding.RowLevelLBinding
import com.aucc.game.databinding.RowLevelRBinding
import com.aucc.game.rest.model.Level

class LevelsAdapter(private val adapterCallback: AdapterCallback) : PagedListAdapter<Level, RecyclerView.ViewHolder>(DiffCallback) {

    val questIds: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) return LevelLViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_level_l, parent, false))

        return LevelRViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_level_r, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LevelLViewHolder)
            holder.bind(getItem(position)!!)
        else if (holder is LevelRViewHolder)
            holder.bind(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 0 else 1
    }

    inner class LevelLViewHolder(private val binding: RowLevelLBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var level: Level
        private var onBind = false

        init {
            itemView.setOnClickListener { if (!onBind) adapterCallback.onLevelClicked(level) }
        }

        fun bind(level: Level) {
            onBind = true
            this.level = level
            binding.level = level
            binding.row = adapterPosition + 1
            binding.completed = questIds.contains(level.id.toString())
            onBind = false
        }
    }

    inner class LevelRViewHolder(private val binding: RowLevelRBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var level: Level
        private var onBind = false

        init {
            itemView.setOnClickListener { if (!onBind) adapterCallback.onLevelClicked(level) }
        }

        fun bind(level: Level) {
            onBind = true
            this.level = level
            binding.level = level
            binding.row = adapterPosition + 1
            binding.completed = questIds.contains(level.id.toString())
            onBind = false
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Level>() {
            override fun areItemsTheSame(oldItem: Level, newItem: Level): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Level, newItem: Level): Boolean =
                oldItem.id == newItem.id &&
                        oldItem.title == newItem.title &&
                        oldItem.description == newItem.description &&
                        oldItem.question == newItem.question
        }
    }

    interface AdapterCallback {
        fun onLevelClicked(level: Level)
    }
}