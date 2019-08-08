package com.yakymovych.simon.yboxsdk.bluetooth

import android.bluetooth.BluetoothSocket

interface ConnectedSocketListener {

    fun listen(socket: BluetoothSocket)
}