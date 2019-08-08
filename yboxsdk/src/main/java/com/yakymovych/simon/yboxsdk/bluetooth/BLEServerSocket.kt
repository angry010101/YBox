package com.yakymovych.simon.yboxsdk.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.Closeable
import java.io.IOException
import java.util.*

open class BLEServerSocket(bluetoothAdapter: BluetoothAdapter,listener: ConnectedSocketListener) : BLESocket(bluetoothAdapter,listener) {
    var socket: BluetoothSocket? = null
    //override var closeSocket: Closeable = this!!.mmServerSocket!!

    private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
        bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(bluetoothAdapter.name, UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))
    }

    override fun run() {
        // Keep listening until exception occurs or joystick closeSocket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            socket= try {
                Log.d("BLE", "Socket success")
                mmServerSocket?.accept()
            } catch (e: IOException) {
                Log.e("BLE", "Socket's accept() method failed", e)
                shouldLoop = false
                null
            }
            socket?.also {

                Log.d("BLE", "Socket also")
                listener.listen(it)
                mmServerSocket?.close()
                shouldLoop = false
            }

            Log.d("BLE", "Socket loop")
        }

        Log.d("BLE", "Socket run return")
    }

}
