package co.kr.deartoday.util

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * @param layoutRoot layout의 rootView
 * @param layoutTouch layout 내 터치 영역 View ID(R.id.~)
 * @param width Figma / Zeplin 상의 터치영역 폭,
 * @param realWidth getScreenWidthDp(),
 * @param height Figma / Zeplin 상의 터치영역 높이,
 * @param realHeight getScreenHeightDp(),
 * @param marginStart Figma / Zeplin 상의 marginStart,
 * @param marginEnd Figma / Zeplin 상의 marginEnd,
 * @param marginTop Figma / Zeplin 상의 marginTop (status bar 제외),
 * @param marginBottom Figma / Zeplin 상의 marginBottom (navigation bar 제외)
 */
fun setTouchLayout(
    layoutRoot: ConstraintLayout,
    layoutTouch: Int,
    width: Int,
    realWidth: Int,
    height: Int,
    realHeight: Int,
    marginStart: Int,
    marginEnd: Int,
    marginTop: Int,
    marginBottom: Int
) {
    val constraintSet = ConstraintSet()
    with(constraintSet) {
        clone(layoutRoot)
        constrainPercentWidth(layoutTouch, width.toFloat() / 360f)
        constrainPercentHeight(layoutTouch, ((height.toFloat() / realHeight.toFloat()) * (realWidth.toFloat() / 360f)))
        setHorizontalBias(layoutTouch, marginStart.toFloat() / (marginStart.toFloat() + marginEnd.toFloat()))
        setVerticalBias(layoutTouch, marginTop.toFloat() / (marginTop.toFloat() + marginBottom.toFloat() + 24f))
        applyTo(layoutRoot)
    }
}