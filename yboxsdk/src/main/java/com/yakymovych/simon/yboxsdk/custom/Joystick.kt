package com.yakymovych.simon.yboxsdk.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Joystick @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val pblack =  Paint(Paint.ANTI_ALIAS_FLAG)
    val pCyan =  Paint(Paint.ANTI_ALIAS_FLAG)
    var dx = 0f
    var dy = 0f
    var center = 0f
    var joystickEventListener: IJoystickEvent? = null

    private var size = 220
    val onTouchListener = object : OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            event?.let {
                dx = (event.x - center)
                dy = (event.y - center)
            }
            joystickEventListener?.onPositionChanged(dx/(size/2),dy/(size/2))
            invalidate()
            return true
        }

    }

    init{
        pblack.color = Color.BLACK
        pCyan.color = Color.CYAN
        this.setOnTouchListener(onTouchListener)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        center = size/2f
        setMeasuredDimension(size, size)

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        pblack.color = Color.BLACK
        canvas?.translate(size/2f,size/2f);
        canvas?.drawCircle(0f, 0f,size/2f,pblack)
        canvas?.drawCircle(dx, dy,size/4f,pCyan)

    }



}

interface IJoystickEvent{
    fun onPositionChanged(dx: Float,dy:Float)
}