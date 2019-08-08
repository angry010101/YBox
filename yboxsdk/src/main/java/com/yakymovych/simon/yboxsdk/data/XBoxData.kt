package com.yakymovych.simon.yboxsdk.data

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.io.Serializable
import java.nio.ByteBuffer

class XBoxData(var buttons: Int = 0,var joystick: Int = 0): Serializable {



    fun prepareByteBuffer() =
            ByteBuffer.allocate(8).putInt(buttons).putInt(joystick).array();

}