package com.yakymovych.simon.yboxsdk.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent

class BluetoothClient {
    companion object {
        val REQUEST_ENABLE_BT = 1
    }
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun isBLESupported() = !(bluetoothAdapter == null)

    fun isBLEEnabled() = bluetoothAdapter?.isEnabled

    fun enableBluetooth(activity: Activity){
        if (!isBLEEnabled()!!) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }

    fun getPairedDevices() = bluetoothAdapter?.bondedDevices

    fun enableDiscoverability(context: Context) {
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        context.startActivity(discoverableIntent)
    }
}