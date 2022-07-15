package co.kr.deartoday.presentation.ui.timetravel

import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.data.model.response.ResponseTimeTravelTape
import co.kr.deartoday.databinding.ActivityTimeTravelBinding
import co.kr.deartoday.presentation.adapter.TapeAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity

class TimeTravelActivity : BaseActivity<ActivityTimeTravelBinding>() {
    private lateinit var tapeAdapter: TapeAdapter
    override val layoutRes: Int
        get() = R.layout.activity_time_travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        tapeAdapter = TapeAdapter()
        binding.rvTimeTravel.adapter = tapeAdapter
        tapeAdapter.tapeList.addAll(
            listOf(
                ResponseTimeTravelTape(
                    "asfjkl1nbbj3j2343284bcjksabfd",
                    "어쩔티비",
                    2022,
                    7,
                    22,
                    "2022.07.09",
                    "이미지URL"
                ),
                ResponseTimeTravelTape(
                    "asfjkl1nbbj3j2343284bcjksabfd",
                    "어쩔티비",
                    2022,
                    7,
                    22,
                    "2022.07.09",
                    "이미지URL"
                ),
                ResponseTimeTravelTape(
                    "asfjkl1nbbj3j2343284bcjksabfd",
                    "어쩔티비",
                    2022,
                    7,
                    22,
                    "2022.07.09",
                    "이미지URL"
                )
            )
        )
        tapeAdapter.notifyDataSetChanged()
    }
}