package co.kr.deartoday.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView


class StickyScrollView : ScrollView, ViewTreeObserver.OnGlobalLayoutListener {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    ) {
        overScrollMode = OVER_SCROLL_NEVER
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    var header: View? = null
        set(value) {
            field = value
            field?.let {
                it.translationZ = 1f
            }
        }

    var stickListener: (View) -> Unit = {}
    var freeListener: (View) -> Unit = {}

    private var mIsHeaderSticky = false

    private var mHeaderInitPosition = 0f

    override fun onGlobalLayout() {
        mHeaderInitPosition = header?.top?.toFloat() ?: 0f
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        val scrolly = t

        if (scrolly > mHeaderInitPosition) {
            stickHeader(scrolly - mHeaderInitPosition)
        } else {
            freeHeader()
        }
    }

    private fun stickHeader(position: Float) {
        header?.translationY = position
        callStickListener()
    }

    private fun callStickListener() {
        if (!mIsHeaderSticky) {
            stickListener(header ?: return)
            mIsHeaderSticky = true
        }
    }

    private fun freeHeader() {
        header?.translationY = 0f
        callFreeListener()
    }

    private fun callFreeListener() {
        if (mIsHeaderSticky) {
            freeListener(header ?: return)
            mIsHeaderSticky = false
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

}