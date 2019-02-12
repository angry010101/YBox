package com.yakymovych.simon.everywhere

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.yakymovych.simon.everywhere.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MVVMApplication : DaggerApplication(){
    private val injection = DaggerAppComponent.builder().create(this)
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        injection

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            //Timber.plant(Timber.DebugTree())
        }
    }

}

