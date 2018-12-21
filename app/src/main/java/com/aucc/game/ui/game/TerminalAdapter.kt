package com.aucc.game.ui.game

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aucc.game.R
import com.aucc.game.databinding.RowTerminalBinding

class TerminalAdapter : RecyclerView.Adapter<TerminalAdapter.LineViewHolder>() {

    private val lines = arrayListOf<TerminalLine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerminalAdapter.LineViewHolder {
        return LineViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_terminal, parent, false))
    }

    override fun getItemCount(): Int {
        return lines.size
    }

    override fun onBindViewHolder(holder: TerminalAdapter.LineViewHolder, position: Int) {
        holder.bind(lines[position])
    }

    fun clearLines() {
        lines.clear()
        notifyDataSetChanged()
    }

    fun addLine(line: TerminalLine) {
        lines.add(line)
        notifyItemInserted(itemCount - 1)
    }

    inner class LineViewHolder(private val binding: RowTerminalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(line: TerminalLine) {
            binding.command = line
        }
    }

    data class TerminalLine(val command: String, val isResponse: Boolean)
}