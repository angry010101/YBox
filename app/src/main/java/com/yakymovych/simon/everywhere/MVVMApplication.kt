package com.yakymovych.simon.everywhere

import android.content.Context
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import androidx.multidex.MultiDex



class MVVMApplication : DaggerApplication(){
    private val injection = DaggerAppComponent.builder().create(this)
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        injection

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

