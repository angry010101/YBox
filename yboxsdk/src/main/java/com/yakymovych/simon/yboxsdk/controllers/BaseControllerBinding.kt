package com.yakymovych.simon.yboxsdk.controllers

abstract class BaseControllerBinding<T> {

    abstract var baseController: BaseController
    abstract fun buttonPress(key: T)
    abstract fun joystickAction(dx: Float, dy: Float)
    abstract fun buttonRelease(key: T)
    var isRecording = false
    get() = baseController.recorder.isRecording
    fun recordingClick() {
        baseController.recordingClick()
    }

    fun recorderEmitClick() {
        baseController.recordingEmit()
    }


}