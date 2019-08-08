package com.yakymovych.simon.yboxsdk.controllers.xbox

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater
import com.yakymovych.simon.yboxsdk.R
import com.yakymovych.simon.yboxsdk.controllers.BaseTouchListener
import com.yakymovych.simon.yboxsdk.custom.IJoystickEvent
import com.yakymovych.simon.yboxsdk.encoders.XBoxEncoder
import kotlinx.android.synthetic.main.xbox_layout.view.*


class XBox @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = XBoxBinding()

    init {
        initializeViews(context)
    }

    private fun initializeViews(context: Context) {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.xbox_layout, this)

        layout.connect.setOnClickListener {
            binding.connect()
        }

        recording.setOnClickListener {
            if (binding.isRecording){
                recording.text = "start"
            }
            else {
                recording.text = "stop"
            }
            binding.recordingClick()
        }

        layout.recorder_emit.setOnClickListener {
            binding.recorderEmitClick()
        }
        layout.button_x.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_X))
        layout.button_y.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_Y))
        layout.button_a.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_A))
        layout.button_b.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_B))

        layout.button_rr.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_RT))
        layout.button_ll.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_LT))
        layout.button_rt.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_RR))
        layout.button_lt.setOnTouchListener(BaseTouchListener(binding, XBoxEncoder.BUTTON_LL))


        layout.joystick.joystickEventListener = object : IJoystickEvent{
            override fun onPositionChanged(dx: Float, dy: Float) {
                binding.joystickAction(dx,dy)
            }
        }
    }

}