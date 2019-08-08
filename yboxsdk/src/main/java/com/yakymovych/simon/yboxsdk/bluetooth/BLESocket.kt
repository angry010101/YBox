package com.yakymovych.simon.yboxsdk.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

abstract class BLESocket(val bluetoothAdapter: BluetoothAdapter,val listener: ConnectedSocketListener): Thread(){


}