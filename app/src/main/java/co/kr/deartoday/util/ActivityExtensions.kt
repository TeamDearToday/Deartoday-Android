package co.kr.deartoday.util

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets

fun Activity.getScreenWidthDp(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        (windowMetrics.bounds.width() - (insets.left + insets.right)).pxToDp()
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels.pxToDp()
    }
}

fun Activity.getScreenHeightDp(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        (windowMetrics.bounds.height() - (insets.top + insets.bottom)).pxToDp()
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels.pxToDp()
    }
}