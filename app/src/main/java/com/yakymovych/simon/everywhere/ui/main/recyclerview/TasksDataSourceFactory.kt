package com.yakymovych.simon.everywhere.ui.main.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.data.Page
import com.yakymovych.simon.everywhere.data.RetroService
import com.yakymovych.simon.everywhere.data.Sort
import com.yakymovych.simon.everywhere.data.Task
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import javax.inject.Inject

class TasksDataSourceFactory
@Inject constructor(private var retroService: RetroService)
    : DataSource.Factory<Int, Task>() {

    var sort: Sort = Sort.ID
    val dataSource = MutableLiveData<DataSource<Int,Task>>()

    override fun create(): DataSource<Int, Task> {
        Timber.d { "CREATING DATASOURCE" }
        val ds = TasksDataSource(retroService)
        ds.sortType = sort.toString()
        dataSource.postValue(ds)
        return ds
    }
}