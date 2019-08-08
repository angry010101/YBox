package com.yakymovych.simon.yboxsdk.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.Closeable
import java.io.IOException
import java.util.*
import android.widget.Toast

class BLEClientSocket(bluetoothAdapter: BluetoothAdapter,listener: ConnectedSocketListener, device: BluetoothDevice) : BLESocket(bluetoothAdapter,listener) {
    var socket: BluetoothSocket? = null
    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        var d: BluetoothSocket? = null
        try {
            d = device.createRfcommSocketToServiceRecord(UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee"))
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("BLE","ERROR ")
        }
        if (d == null){
            Log.e("BLE","D IS NULL")
        }
        d
    }

    public override fun run() {
        // Cancel discovery because it otherwise slows down the connection.
        bluetoothAdapter.cancelDiscovery()

        mmSocket?.use { socket ->
            // Connect to the remote device through the closeSocket. This call blocks
            // until it succeeds or throws an exception.


            socket.connect()
            // The connection attempt succeeded. Perform work associated with
            // the connection in joystick separate thread.

            listener.listen(socket)
            while (true){

            }
        }
    }

    // Closes the client closeSocket and causes the thread to finish.

}
