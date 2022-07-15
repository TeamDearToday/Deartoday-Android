package co.kr.deartoday.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class TapeItemDecoration(
    private val offset: Int,
    private val lineColor: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = offset.dpToPx()
        outRect.set(0, 0 ,0, offset )
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val strokeWidth = 1
        val paint = Paint().apply {
            this.color = lineColor
            this.style = Paint.Style.STROKE
            this.strokeWidth = strokeWidth.dpToPx().toFloat()
        }

        parent.children.forEach { child ->
            c.drawLine(
                (child.left).toFloat(),
                (child.bottom).toFloat()+5.dpToPx(),
                (child.right).toFloat(),
                (child.bottom).toFloat()+5.dpToPx(),
                paint)
        }
    }
}