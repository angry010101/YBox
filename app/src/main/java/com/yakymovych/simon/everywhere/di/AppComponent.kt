package com.yakymovych.simon.everywhere.di

import android.app.Application
import com.yakymovych.simon.everywhere.MVVMApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class,FragmentBuilder::class))
interface AppComponent : AndroidInjector<MVVMApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MVVMApplication>() {
    }
}