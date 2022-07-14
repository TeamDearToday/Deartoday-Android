package co.kr.deartoday.presentation.ui.timetravel

import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityTimeTravelBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity

class TimeTravelActivity : BaseActivity<ActivityTimeTravelBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_time_travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}