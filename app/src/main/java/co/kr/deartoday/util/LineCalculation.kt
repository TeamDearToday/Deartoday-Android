package co.kr.deartoday.util

import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/**
 * Calculate max lines according height
 *
 * @param text- text need to be set
 * @param lineCount- invoked with -1 if view height is enough to show full text,
 *                   otherwise invoked with maxLines
 */
inline fun TextView.calculateMaxLines(text: String, crossinline lineCount: (Int) -> (Unit)) {
    val params: PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(this)
    val ref: WeakReference<TextView>? = WeakReference(this)
    GlobalScope.launch(Dispatchers.Default) {
        val computedText = PrecomputedTextCompat.create(text, params)
        ref?.get()?.apply {
            TextViewCompat.setPrecomputedText(this, computedText)
            GlobalScope.launch(Dispatchers.Main) {
                ref.get()?.let {
                    val bounds = it.getLineBounds(0, null)
                    val heightRequired = bounds * it.lineCount
                    val maxLines = if (heightRequired > height) {
                        height / bounds
                    } else -1
                    lineCount.invoke(maxLines)
                }
            }
        }
    }
}