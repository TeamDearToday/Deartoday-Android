package co.kr.deartoday.presentation.ui.timemachine

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityTimeMachineBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel

@AndroidEntryPoint
class TimeMachineActivity : BaseActivity<ActivityTimeMachineBinding>() {
    val mainScope = this.lifecycleScope

    override val layoutRes: Int
        get() = R.layout.activity_time_machine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentContainer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun initFragmentContainer() {
        supportFragmentManager.commit {
            add<TimeMachineImagePickerFragment>(R.id.fcv_time_machine)
        }
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("시간 여행을 그만두시겠어요?")
            .setMessage("그만둔 시간여행은 저장되지 않아요")
            .setPositiveButton("계속하기") { _, _ -> }
            .setNegativeButton("그만두기") { _, _ -> finish() }
            .create()
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.blue_558fff))
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.dark_gray_131313))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, TimeMachineActivity::class.java)
    }
}