package com.example.surfaceviewexample

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.view.SurfaceHolder
import android.view.SurfaceView

class CustonSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private var paint : Paint? = Paint()
    private var surfaceHolder: SurfaceHolder = holder
    private var circleX: Float? = 0f
    private var circleY: Float? = 0f

    init {
        surfaceHolder.addCallback(this)

        paint?.color = Color.WHITE
        setBackgroundColor(Color.BLACK)

        // set the current surfaceview at top of the view tree
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSLUCENT)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        drawBall()
    }

    fun drawBall(){
        // Get and lock canvas object from surfaceHolder
        val canvas = surfaceHolder.lockCanvas()
        val paintSurfaceBack = Paint()
        paintSurfaceBack.color = Color.BLACK

        // Draw the surfaceView background colour
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintSurfaceBack)

        // Draw the Circle
        canvas.drawCircle(circleX!!, circleY!!, 220f, paint)

        // Unlock the canvas object and post the new draw
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun drawRect(){
        val canvas = surfaceHolder.lockCanvas()
        val paintSurfaceBack = Paint()

        paintSurfaceBack.color = Color.BLACK
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintSurfaceBack)

        // Draw the rectangle
        canvas.drawRect(circleX!!, circleY!!, circleX!! + 300, circleY!! + 300, paint)

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun getCircleX() = circleX
    fun getCircleY() = circleY
    fun getPaint() = paint

    fun setCircleX(circleX: Float?){
        this.circleX = circleX
    }

    fun setCircleY(circleY: Float?){
        this.circleY = circleY
    }

    fun setPaint(paint: Paint?){
        this.paint = paint
    }
}