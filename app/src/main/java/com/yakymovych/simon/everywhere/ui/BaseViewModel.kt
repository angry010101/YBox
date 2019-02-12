package com.yakymovych.simon.everywhere.ui

import android.arch.lifecycle.ViewModel
import android.media.MediaRecorder


import android.util.Log
import com.yakymovych.simon.everywhere.HOSTNAME
import com.yakymovych.simon.everywhere.PORT
import com.yakymovych.simon.everywhere.data.Repository
import java.io.IOException
import java.net.Socket
import android.system.Os.socket
import android.os.ParcelFileDescriptor
abstract class BaseViewModel(var repository: Repository) : ViewModel(){

}