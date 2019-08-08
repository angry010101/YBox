package com.yakymovych.simon.yboxsdk.controllers

import android.view.MotionEvent
import android.view.View

class BaseTouchListener<T>(val binding: BaseControllerBinding<T>, val key: T) : View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.getAction()) {
            MotionEvent.ACTION_DOWN ->{
                binding.buttonPress(key)
                return true
            }
            MotionEvent.ACTION_UP -> {
                binding.buttonRelease(key)
                return true
            }
        }
        return false
    }
}