package com.yakymovych.simon.everywhere

import android.Manifest


val REQUEST_VIDEO_PERMISSIONS = 1
val VIDEO_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)

val HOSTNAME = "10.0.2.2"
val BASE_URL = "http://example.com"

val PORT = 64232