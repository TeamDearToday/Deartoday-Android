package co.kr.deartoday.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MessageBoxItemDecoration(
    private val margin: Int
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val margin = margin.dpToPx()
        outRect.set(margin, margin, margin, margin)
    }
}