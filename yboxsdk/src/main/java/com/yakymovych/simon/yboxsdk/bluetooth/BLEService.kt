package com.yakymovych.simon.yboxsdk.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BLEService(handler: Handler) {
    var bluetoothClient: BluetoothClient
    var bluetoothAdapter: BluetoothAdapter
    lateinit var bleHelper: BLEHelper

    var send = MutableLiveData<ByteArray>()

    init {
        bluetoothClient = BluetoothClient()
        bluetoothAdapter = bluetoothClient.bluetoothAdapter!!
    }
    var listener = object : ConnectedSocketListener{
        override fun listen(socket: BluetoothSocket) {
            Log.d("BLE","LISTENING")
            bleHelper = BLEHelper(handler,socket)
        }
    }


    fun getListDevices(): MutableSet<BluetoothDevice>? {
        return bluetoothAdapter.bondedDevices
    }

    fun connectAsServer(){
        Log.d("BLE","CONNECTING")
        val conn = BLEServerSocket(bluetoothAdapter,listener)
        conn.start()
    }

    fun connectAsClient(device: BluetoothDevice){
        val conn = BLEClientSocket(bluetoothAdapter,listener,device)
        conn.start()
    }

    fun write(bytes: ByteArray){
        bleHelper.write(bytes)
    }
}