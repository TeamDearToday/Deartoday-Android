package co.kr.deartoday.util

import androidx.fragment.app.Fragment

fun Fragment.getScreenWidthDp(): Int = requireActivity().getScreenWidthDp()
fun Fragment.getScreenHeightDp(): Int = requireActivity().getScreenHeightDp()