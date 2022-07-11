package co.kr.deartoday.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

fun fadeInAnimator(view: View, duration: Long): ObjectAnimator {
    val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
    fadeIn.duration = duration
    return fadeIn
}

fun slideUpAnimator(view: View, duration: Long): ObjectAnimator {
    val slideUp = ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 100.0f, 10.0f)
    )
    slideUp.duration = duration
    return slideUp
}