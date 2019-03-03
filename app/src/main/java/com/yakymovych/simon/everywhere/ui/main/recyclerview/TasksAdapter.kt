package com.yakymovych.simon.everywhere.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.data.Task
import timber.log.Timber

class TasksAdapter(val onClick: (Task) -> Unit) : PagedListAdapter<Task, TaskViewHolder>(Task.DiffCallback) {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskViewHolder  =
                TaskViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_task, parent, false))


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            Timber.d(TAG, "Binding view holder at position $position")
            holder.task = getItem(position)
            holder.title.setOnClickListener {
                onClick(holder.task!!)
            }
    }

}