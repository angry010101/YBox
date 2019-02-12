package com.yakymovych.simon.everywhere.di

import com.yakymovych.simon.everywhere.ui.camera.Camera2VideoFragment
import com.yakymovych.simon.everywhere.ui.camera.Camera2VideoFragmentModule
import com.yakymovych.simon.everywhere.ui.main.MainActivity
import com.yakymovych.simon.everywhere.ui.main.MainActivityModule
import dagger.Module
import dagger.Subcomponent
import dagger.android.ContributesAndroidInjector
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity


    //@FragmentScope
    //@ContributesAndroidInjector(modules = arrayOf(Camera2VideoFragmentModule::class))
    //abstract fun bindCamera2VideoFragment(): Camera2VideoFragment

}


@Scope
@Retention
annotation class FragmentScope



@Scope
@Retention
annotation class ActivityScope
