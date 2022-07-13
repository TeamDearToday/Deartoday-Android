package co.kr.deartoday.presentation.ui.timemachine

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityMainBinding
import co.kr.deartoday.databinding.ActivityTimeMachineBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class TimeMachineActivity : BaseActivity<ActivityTimeMachineBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_time_machine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDateView()
        initOnClickListener()
    }

    private fun initDateView() {
        val todayFormat = SimpleDateFormat("yyyyMMdd")
        val today = todayFormat.format(Date())
        with(binding) {
            tvTodayYear.text = today.substring(0, 4)
            tvTodayMonth.text = today.substring(4, 6)
            tvTodayDay.text = today.substring(6, 8)
        }
    }

    private fun initOnClickListener() {
        binding.ivVideoTape.setOnClickListener {

        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, TimeMachineActivity::class.java)
    }
}