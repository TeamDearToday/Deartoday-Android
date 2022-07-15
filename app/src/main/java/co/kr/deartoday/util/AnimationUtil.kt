package co.kr.deartoday.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView

fun fadeInAnimator(view: View, duration: Long): ObjectAnimator {
    val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
    fadeIn.duration = duration
    return fadeIn
}

fun fadeOutAnimator(view: View, duration: Long): ObjectAnimator {
    val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
    fadeOut.duration = duration
    return fadeOut
}

fun slideUpAnimator(view: View, duration: Long): ObjectAnimator {
    val slideUp = ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 100.0f, 10.0f)
    )
    slideUp.duration = duration
    return slideUp
}

fun textCounterAnimator(textView: TextView, fromValue: String, toValue: String, duration: Long): ValueAnimator {
    val textCounter = ValueAnimator()
    textCounter.setObjectValues(fromValue.toInt(), toValue.toInt())
    textCounter.addUpdateListener { animation -> textView.text = animation.animatedValue.toString() }
    textCounter.duration = duration
    return textCounter
}