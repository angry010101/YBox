package com.yakymovych.simon.yboxsdk.custom

import android.util.Log

object Algorithms {

    fun prepareButtons(buttonState: Int): ByteArray {
        return byteArrayOf( buttonState.toChar().toByte(), (buttonState / (256)).toChar().toByte())
    }


    val size = 7.0;
    fun mapJoystick(x: Float,y: Float): Int {
        val ix = ((Math.pow(2.0, size).toInt()/2)*(x+1)).toInt() shl (size.toInt()+1)
        val iy = (Math.pow(2.0, size).toInt()*(y+1)).toInt()
        Log.d("ALGORITHMS","JOYSTICK " + x + " " + ix + " " + y + " " + iy)
        return ix+iy
    }

}