package com.example.youtubemusic.constant

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

object Const {
    val rotationAnimation = RotateAnimation(
        0F, 360F,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    ).apply {
        duration = 3600
        interpolator = LinearInterpolator()
        repeatCount = Animation.INFINITE
    }
}