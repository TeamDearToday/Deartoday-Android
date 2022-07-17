package co.kr.deartoday.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PastPhotoItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildLayoutPosition(view)

        if (position == 0) {
            outRect.left = (offset + 1).dpToPx()
            outRect.right = offset.dpToPx()
        } else if (position == parent.childCount - 1) {
            outRect.right = (offset + 1).dpToPx()
        } else {
            outRect.right = offset.dpToPx()
        }
    }
}