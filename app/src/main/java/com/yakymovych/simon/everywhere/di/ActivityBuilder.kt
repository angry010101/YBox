package com.yakymovych.simon.everywhere.di


import com.yakymovych.simon.everywhere.ui.main.MainActivity
import com.yakymovych.simon.everywhere.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity

}


@Scope
@Retention
annotation class FragmentScope



@Scope
@Retention
annotation class ActivityScope
