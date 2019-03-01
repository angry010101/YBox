package com.yakymovych.simon.everywhere.ui.main.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater



abstract class BaseAdapter : RecyclerView.Adapter<MyTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return MyTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyTaskViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }


    protected abstract fun getObjForPosition(position: Int): Any

    protected abstract fun getLayoutIdForPosition(position: Int): Int
}