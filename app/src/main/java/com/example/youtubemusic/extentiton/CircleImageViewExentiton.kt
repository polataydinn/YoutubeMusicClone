package com.example.youtubemusic.extentiton

import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


val CircleImageView.timerContainer: TimerContainer
    get() = TimerContainer(this)


fun CircleImageView.rotate(isRotating: Boolean) {
    if (isRotating) {
        timerContainer.start()
    } else {
        timerContainer.cancel()
    }
}

class TimerContainer(private val civ: CircleImageView) {
    var timerTask: RotateTimerTask? = null
    var timer: Timer? = null

    fun start() {
        if (timer == null && timerTask == null) {
            timer = Timer()
            timerTask = RotateTimerTask(civ)
            timer?.scheduleAtFixedRate(timerTask, 0, 32)
        }
    }

    fun cancel() {
        if (timer != null && timerTask != null) {
            timerTask?.cancel()
            timer?.cancel()
            timer?.purge()
            timerTask == null
            timer == null
        }
    }
}

class RotateTimerTask(private val civ: CircleImageView) : TimerTask() {
    override fun run() {
        civ.rotation += 2
    }

}