package com.yakymovych.simon.everywhere.ui.main

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.yakymovych.simon.everywhere.data.Task
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import androidx.paging.LivePagedListBuilder
import com.yakymovych.simon.everywhere.ui.main.recyclerview.TasksDataSourceFactory
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.data.Sort
import com.yakymovych.simon.everywhere.ui.main.recyclerview.TasksDataSource

import com.yakymovych.simon.everywhere.R


class MainActivityViewModel(var tasksFactory: TasksDataSourceFactory):BaseViewModel() {
    var tasks: LiveData<PagedList<Task>>
    var sort: Int  = R.id.action_sort_id
    var isResfreshing = MutableLiveData<Boolean>()
    var pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(LIMIT).build()

    companion object {
        var LIMIT = 15
    }
    init  {
        tasks = LivePagedListBuilder<Int, Task>(tasksFactory, pagedListConfig).
                setInitialLoadKey(0).
                build()

    }

    var refreshListener = OnRefreshListener {
        this.isResfreshing.value = true
        refreshTasks()
    }

    fun selectSort(sort: Sort,resourse: Int){
        Timber.d { "SORT SELECTED: " + sort.name }
        this.sort = resourse
        tasksFactory.sort = sort
        refreshTasks()
    }
    fun refreshTasks(){
        Timber.d { "REFRESHING" }
        tasksFactory.dataSource.value?.invalidate()
    }
}