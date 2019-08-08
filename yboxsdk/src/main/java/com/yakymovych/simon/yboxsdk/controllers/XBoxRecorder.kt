package com.yakymovych.simon.yboxsdk.controllers

import android.util.Log
import com.yakymovych.simon.yboxsdk.controllers.xbox.XBoxCore
import com.yakymovych.simon.yboxsdk.data.XBoxData
import java.util.*

class XBoxRecorder(var core: XBoxCore) {

    private val list = mutableListOf<State>()
    private var startTime = System.currentTimeMillis()
    private var lastActionTime = System.currentTimeMillis()

    fun processNextState(state: XBoxData) {
        if (isRecording){
            if (lastActionTime == 0.toLong()){
                lastActionTime = System.currentTimeMillis()
            }
            val t = System.currentTimeMillis()
            list.add(State(t-lastActionTime,XBoxData(state.buttons,state.joystick)))
            lastActionTime = t
        }
    }

    var isRecording: Boolean = false
    set(value) {
        if (value == true){
            list.clear()
            startTime = System.currentTimeMillis()
            lastActionTime = 0
        }
        field = value
    }

    private data class State(var ts: Long,var item: XBoxData)

    fun emit(){
        Log.d("RECORDER","" + list.size
        )
        list.forEach{
            Log.d("RECORDER","" + it.item.buttons + " " +it.item.joystick)
        }
        Thread{
            for (i in list){
                Thread.sleep(i.ts)
                core.state = i.item
                core.notifyStateChanged()
            }
        }.start()
    }
}