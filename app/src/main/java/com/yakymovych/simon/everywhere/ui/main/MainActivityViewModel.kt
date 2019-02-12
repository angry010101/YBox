package com.yakymovych.simon.everywhere.ui.main

import android.media.MediaRecorder
import android.os.ParcelFileDescriptor
import android.util.Log
import com.yakymovych.simon.everywhere.HOSTNAME
import com.yakymovych.simon.everywhere.PORT
import com.yakymovych.simon.everywhere.data.Repository
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.io.IOException
import java.net.Socket

class MainActivityViewModel(repository: Repository):BaseViewModel(repository) {
    var socket: Socket? = null
    lateinit var mediaPlayer: MediaRecorder

    fun createConnection(): Single<Unit>? {
        return repository.openConnection()
    }

    fun start(){
        mediaPlayer.setOutputFile(repository.fileDescriptor.fileDescriptor)
    }

    fun closeConnection(){
        repository.closeConnection()
    }

}