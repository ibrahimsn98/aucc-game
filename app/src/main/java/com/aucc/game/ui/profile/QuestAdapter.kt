package com.aucc.game.ui.profile

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aucc.game.R
import com.aucc.game.data.quest.Quest
import com.aucc.game.databinding.RowQuestBinding

class QuestAdapter : PagedListAdapter<Quest, QuestAdapter.QuestViewHolder>(Quest.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        return QuestViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.row_quest, parent, false))
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class QuestViewHolder(private val binding: RowQuestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quest: Quest) {
            binding.quest = quest
        }
    }
}