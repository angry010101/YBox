package com.yakymovych.simon.everywhere.ui.main.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class MyTaskViewHolder(binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {
    private val binding: ViewDataBinding

    fun bind(obj: Any) {
    //    binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }

    init {
        this.binding = binding
    }
}