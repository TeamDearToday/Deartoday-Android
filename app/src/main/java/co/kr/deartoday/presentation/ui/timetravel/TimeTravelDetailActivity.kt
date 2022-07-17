package co.kr.deartoday.presentation.ui.timetravel

import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityTimeTravelDetailBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity

class TimeTravelDetailActivity : BaseActivity<ActivityTimeTravelDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_time_travel_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.svSticky.run {
            this.header = binding.clTitleBundle
        }
    }

}