package com.yakymovych.simon.everywhere.ui.main

import com.yakymovych.simon.everywhere.data.RetroService
import com.yakymovych.simon.everywhere.ui.main.recyclerview.TasksDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideTasksDataSourceFactory(retroService: RetroService) = TasksDataSourceFactory(retroService)

    @Provides
    fun provideViewModel(tasksDataSourceFactory: TasksDataSourceFactory)
            = MainActivityViewModel(tasksDataSourceFactory)
}