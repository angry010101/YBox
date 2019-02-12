package com.yakymovych.simon.everywhere.di

import com.yakymovych.simon.everywhere.ui.camera.Camera2VideoFragment
import com.yakymovych.simon.everywhere.ui.camera.Camera2VideoFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = arrayOf(Camera2VideoFragmentModule::class))
    abstract fun bindCamera2VideoFragment(): Camera2VideoFragment


}