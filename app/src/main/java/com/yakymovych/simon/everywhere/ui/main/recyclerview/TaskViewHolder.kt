package com.yakymovych.simon.everywhere.ui.main.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.data.Task
import com.yakymovych.simon.everywhere.utils.TextUtils
import kotlinx.android.synthetic.main.view_holder_task.view.*


class TaskViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val title: TextView
    val dueBy: TextView
    val priority: TextView

    var task: Task? = null
    set(value) {
        title.text = value?.title ?: ""
        val date = value?.dueBy ?: 0
        if (date > 0)
            dueBy.text = TextUtils.longToStringDate(date)
        else
            dueBy.text = ""
        priority.text = value?.priority
        field = value
    }

    init {
        title= view.task_title
        dueBy= view.task_dueby
        priority= view.task_priority
    }
}