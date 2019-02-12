package com.yakymovych.simon.everywhere.ui.main

import android.os.Bundle
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.ui.BaseActivity
import com.yakymovych.simon.everywhere.ui.camera.Camera2VideoFragment
import javax.inject.Inject


class MainActivity : BaseActivity() {

//    @Inject
//    lateinit var viewModel: MainActivityViewModel
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2VideoFragment.newInstance())
                    .commit();
        }

        // Example of a call to a native method
        //sample_text.text = stringFromJNI()
    }





    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
