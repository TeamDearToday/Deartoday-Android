package co.kr.deartoday.util

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import co.kr.deartoday.R

class CustomDialog(val context: Context) {
    private val dialog = Dialog(context)

    fun showConfirmDialog(@LayoutRes layout: Int, inset: Int, content: String) {
        dialog.setContentView(layout)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(inset)
        dialog.setCancelable(false)
        dialog.findViewById<TextView>(R.id.tv_content).text = content
        dialog.show()
        dialog.findViewById<View>(R.id.view_ok).setOnClickListener {
            dialog.dismiss()
        }
    }
}