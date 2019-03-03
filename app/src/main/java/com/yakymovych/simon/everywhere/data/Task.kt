package com.yakymovych.simon.everywhere.data

import androidx.recyclerview.widget.DiffUtil


data class Task(
        val dueBy: Long,
        val id: Int,
        val priority: String,
        val title: String
){
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean
                    = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean
                    = oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.dueBy == newItem.dueBy
        }
    }
}