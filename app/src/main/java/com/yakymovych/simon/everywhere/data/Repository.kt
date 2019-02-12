package com.yakymovych.simon.everywhere.data

import android.media.MediaRecorder
import android.os.ParcelFileDescriptor
import android.util.Log
import com.yakymovych.simon.everywhere.HOSTNAME
import com.yakymovych.simon.everywhere.PORT
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(private val retroService: RetroService,private val schedulerProvider: SchedulerProvider){

    lateinit var fileDescriptor : ParcelFileDescriptor
    var socket: Socket? = null

    fun openConnection(): Single<Unit>? {
        return Single.fromCallable {
            closeConnection();
            try {
                socket = Socket(HOSTNAME, PORT)
            }
            catch (e: IOException) {
                e.printStackTrace()
                Log.d("SOCKET","ERROR CONNECTING")
                throw Exception("ERROR CONNECTING");
            }

        }.doAfterSuccess {
            Log.d("SOCKET","SOCKET CREATED")
            fileDescriptor = ParcelFileDescriptor.fromSocket(socket)
        }.subscribeOn(schedulerProvider.backgroundScheduler)
    }


    fun closeConnection(){
        Thread {
            if (socket != null && !socket!!.isClosed()) {
                try {
                    socket!!.close()
                } catch (e: IOException) {
                    Log.e("ERROR", "Невозможно закрыть сокет: " + e.message)
                } finally {
                    socket = null
                }
            }
            socket = null
        }
    }

    fun sendData(data: ByteArray) {
        Thread{
            if (socket == null || socket!!.isClosed()) {
                throw Exception("Невозможно отправить данные. Сокет не создан или закрыт");
            }
            try {
                socket!!.getOutputStream().write(data);
                socket!!.getOutputStream().flush();
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


}