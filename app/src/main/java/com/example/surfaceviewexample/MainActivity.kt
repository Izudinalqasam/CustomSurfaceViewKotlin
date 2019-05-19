package com.example.surfaceviewexample

import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var custonSurfaceView: CustonSurfaceView
    private var drawBall = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView(){
        custonSurfaceView = CustonSurfaceView(baseContext)
        custonSurfaceView.setOnTouchListener(this)

        customViewLayout.addView(custonSurfaceView)

        redButton.setOnClickListener { drawBall = true }
        greenButton.setOnClickListener { drawBall = false }
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v is SurfaceView){
            var x = event?.x
            var y = event?.y

            custonSurfaceView.setCircleX(x)
            custonSurfaceView.setCircleY(y)

            if (drawBall){
                val paint = Paint()
                paint.color = Color.WHITE
                custonSurfaceView.setPaint(paint)

                custonSurfaceView.drawBall()
            }else{
                val paint = Paint()
                paint.color = Color.YELLOW
                custonSurfaceView.setPaint(paint)

                custonSurfaceView.drawRect()
            }

            return true
        }else{
            return false
        }
    }

}
