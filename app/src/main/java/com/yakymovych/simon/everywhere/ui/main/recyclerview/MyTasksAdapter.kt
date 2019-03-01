package com.yakymovych.simon.everywhere.ui.main.recyclerview

import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.data.Task

class MyTasksAdapter(var tasks: List<Task>) : BaseAdapter() {
    override fun getObjForPosition(position: Int): Any {
        return tasks[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.view_holder_task
    }

    override fun getItemCount() = tasks.size
}