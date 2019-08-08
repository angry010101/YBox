package com.yakymovych.simon.yboxsdk.controllers.xbox

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.yakymovych.simon.yboxsdk.controllers.BaseController
import com.yakymovych.simon.yboxsdk.controllers.XBoxRecorder
import com.yakymovych.simon.yboxsdk.custom.Algorithms
import com.yakymovych.simon.yboxsdk.data.XBoxData

class XBoxCore : BaseController() {
    var recorder: XBoxRecorder = XBoxRecorder(this)

    override val handler = object : Handler(Looper.getMainLooper()) {
        /*
         * handleMessage() defines the operations to perform when
         * the Handler receives joystick new Message to process.
         */
        override fun handleMessage(inputMessage: Message) {
            // Gets the image task from the incoming Message object.

        }
    }


    fun recordingClick() {
        recorder.isRecording = !recorder.isRecording
        if (recorder.isRecording){

            //start
        }
        else{
            //stop
        }
    }

    fun recordingEmit() {
        recorder.emit()
    }


    override var buffer: ByteArray = byteArrayOf()

    var buttonState: Int = 0
    var x = 0f
    var y = 0f
    var state = XBoxData()


    fun actionPress(key: Int) {
        buttonState = buttonState or key
    }

    fun actionRelease(key: Int) {
        buttonState = buttonState and key.inv()
    }

    fun updateState(){
        val joystick_byte = Algorithms.mapJoystick(x,y)
        state.joystick = joystick_byte
        state.buttons = buttonState

    }

    override fun notifyStateChanged(){
        (recorder).processNextState(state)
        buffer = state.prepareByteBuffer()
        Log.d("DATA", "" + state.buttons + " "+ state.joystick)
        send()
    }
}