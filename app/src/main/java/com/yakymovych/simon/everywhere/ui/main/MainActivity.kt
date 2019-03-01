package com.yakymovych.simon.everywhere.ui.main

import android.os.Bundle
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.ui.BaseActivity
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import javax.inject.Inject


class MainActivity : BaseActivity() {
    override fun getBaseViewModel(): BaseViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.requestsTasks()
        // Example of a call to a native method
        //sample_text.text = stringFromJNI()
    }

}
