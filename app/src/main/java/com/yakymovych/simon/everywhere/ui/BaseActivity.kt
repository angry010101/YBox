package com.yakymovych.simon.everywhere.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {
    abstract fun getBaseViewModel(): BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeBaseViewModel()
    }
    private fun observeBaseViewModel(){
        getBaseViewModel().toastMessage.observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
        })
    }

}