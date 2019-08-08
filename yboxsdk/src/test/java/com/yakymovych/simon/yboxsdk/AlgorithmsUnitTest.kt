package com.yakymovych.simon.yboxsdk

import com.yakymovych.simon.yboxsdk.custom.Algorithms
import com.yakymovych.simon.yboxsdk.encoders.XBoxEncoder
import org.junit.Test


import org.junit.Assert.*

class AlgorithmsUnitTest {

    @Test
    fun testAlgorithmsExt(){
//        assertEquals(Algorithms.prepareButtons()-1f,-1f,0), byteArrayOf((-128).toByte(),0.toByte(),0.toByte()))
//        byte range from -128 to 127
    }


    @Test
    fun testJoystick(){
        assertEquals(Algorithms.mapJoystick(-1f,-1f), (-128).toByte())
        assertEquals(Algorithms.mapJoystick(0f,-1f), (0).toByte())
        assertEquals(Algorithms.mapJoystick(0f,1f), (15).toByte())
        assertEquals(Algorithms.mapJoystick(1f,1f), (127).toByte())
    }


}