package com.aucc.game.ui.levels

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aucc.game.R
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.RowLevelLBinding
import com.aucc.game.databinding.RowLevelRBinding

class LevelsAdapter(private val adapterCallback: AdapterCallback) : PagedListAdapter<Level, RecyclerView.ViewHolder>(Level.DiffCallback) {

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

        init {
            itemView.setOnClickListener { adapterCallback.onRequestClicked(level) }
        }

        fun bind(level: Level) {
            this.level = level
        }
    }

    inner class LevelRViewHolder(private val binding: RowLevelRBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var level: Level

        init {
            itemView.setOnClickListener { adapterCallback.onRequestClicked(level) }
        }

        fun bind(level: Level) {
            this.level = level
        }
    }

    interface AdapterCallback {
        fun onRequestClicked(level: Level)
    }
}