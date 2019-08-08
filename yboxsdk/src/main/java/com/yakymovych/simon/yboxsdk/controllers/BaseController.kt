package com.yakymovych.simon.yboxsdk.controllers

import android.os.Handler
import com.yakymovych.simon.yboxsdk.bluetooth.BLEService

abstract class BaseController {
    abstract fun notifyStateChanged()
    abstract val handler: Handler
    abstract var buffer: ByteArray

    val bleService by lazy {
        BLEService(handler)
    }
    protected fun send(){
        bleService.write(buffer)
    }
    protected fun getOutputStream() = bleService.bleHelper.getOutputStream()


}