package com.yakymovych.simon.everywhere.ui.main.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yakymovych.simon.everywhere.data.Task

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import android.R.attr.data
import androidx.paging.PageKeyedDataSource
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.data.Page
import com.yakymovych.simon.everywhere.data.RetroService
import com.yakymovych.simon.everywhere.data.requests.Meta
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy


class TasksDataSource(private val retroService: RetroService)
        : PageKeyedDataSource<Int, Task>() {

    private var items: MutableList<Task> = ArrayList()
    var sortType = "id asc"
    @Volatile
    private lateinit var meta: Meta

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Task>) {

        retroService.getTasks(1,sortType).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it.tasks)
                    processMeta(it.meta)
                    callback.onResult(it.tasks,1 ,2)
                },
                onError = { throwable ->
                    callback.onResult(arrayListOf(),0,0)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Task>) {
        retroService.getTasks(params.key,sortType).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it.tasks)
                    processMeta(it.meta)
                    callback.onResult(it.tasks,params.key+1)
                },
                onError = { throwable ->
                    callback.onResult(arrayListOf(),params.key)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Task>) {

    }
    fun processMeta(meta: Meta){
        this.meta = meta
    }
}