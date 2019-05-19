package com.example.surfaceviewexample

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_simple_surface_view.*
import java.io.IOException
import java.lang.Exception

class SimpleSurfaceView : AppCompatActivity(), SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var surfaceHolder: SurfaceHolder
    private val VIDEO_PATH = "https://www.youtube.com/embed/EOQ8dvjbI4I"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_surface_view)

        initView()
    }

    private fun initView(){
        surfaceHolder = simple_surfaceview_video.holder
        surfaceHolder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        initMediaPlayer()
    }

    private fun initMediaPlayer(){
        mediaPlayer = MediaPlayer()

        try {
            mediaPlayer?.apply {
                setDisplay(surfaceHolder)
                setDataSource(VIDEO_PATH)
                prepare()
                setOnPreparedListener(this@SimpleSurfaceView)
                setAudioStreamType(AudioManager.STREAM_MUSIC)
            }
        }catch (e : IOException){
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        releaseMediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer?.start()
    }

    private fun releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}
