package com.yakymovych.simon.yboxsdk.bluetooth

import java.nio.charset.Charset

object BLEUtils {
    fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    fun stringToByteArray(s: String): ByteArray = s.toByteArray();

}