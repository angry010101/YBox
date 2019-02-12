package com.yakymovych.simon.everywhere.ui.camera

import com.yakymovych.simon.everywhere.data.Repository
import com.yakymovych.simon.everywhere.ui.main.MainActivityViewModel
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Camera2VideoFragmentModule {
    @Provides
    fun provideViewModel(repository: Repository)
            = MainActivityViewModel(repository)
}