package com.yakymovych.simon.everywhere.ui

import androidx.lifecycle.Observer
import android.util.Log
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {
    abstract fun getBaseViewModel(): BaseViewModel

    private fun observeBaseViewModel() {
        getBaseViewModel().toastMessage.observe(this, Observer {
            Log.d("TAG", "toastMessage changed " + it)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}