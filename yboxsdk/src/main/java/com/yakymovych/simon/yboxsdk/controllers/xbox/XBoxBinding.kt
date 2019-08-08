package com.yakymovych.simon.yboxsdk.controllers.xbox

import android.util.Log
import com.yakymovych.simon.yboxsdk.controllers.BaseController
import com.yakymovych.simon.yboxsdk.controllers.BaseControllerBinding


class XBoxBinding : BaseControllerBinding<Int>() {
    override fun buttonPress(key: Int) {

        xbox.actionPress(key)
        xbox.updateState()
        xbox.notifyStateChanged()
    }

    override fun buttonRelease(key: Int) {

        xbox.actionRelease(key)
        xbox.updateState()
        xbox.notifyStateChanged()
    }


    val e = 0.001f
    private var x=0f;
    private var y=0f;
    //dx, dy - distance from the center -1..0..1
    override fun joystickAction(dx: Float, dy: Float) {
        var dxc = dx
        var dyc = dy
        if (Math.abs(dx) > 1.0){
            dxc = Math.signum(dx)-e*Math.signum(dx)
        }
        if (Math.abs(dy) > 1.0){
            dyc = Math.signum(dy)-e*Math.signum(dy)
        }
        xbox.x = dxc
        xbox.y = dyc
        xbox.updateState()
        xbox.notifyStateChanged()
    }

    private var xbox: XBoxCore = XBoxCore()
    override var baseController: BaseController = xbox

    fun connect(){
        if (xbox.bleService.bluetoothClient.isBLESupported()){
            //TODO find device
            val device = xbox.bleService.getListDevices()?.last()!!
            Log.d("BLE",device.name)
            xbox.bleService.connectAsClient(device)
        }
        else {
            Log.d("BLE","BLE IS NOT SUPPORTED")
        }
    }


}