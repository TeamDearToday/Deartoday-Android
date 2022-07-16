package co.kr.deartoday.presentation.ui.timetravel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.data.model.response.TapesResponse
import co.kr.deartoday.databinding.ActivityTimeTravelBinding
import co.kr.deartoday.presentation.adapter.TapeAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.messagebox.MessageBoxActivity
import co.kr.deartoday.util.TapeItemDecoration

class TimeTravelActivity : BaseActivity<ActivityTimeTravelBinding>() {
    private lateinit var tapeAdapter: TapeAdapter
    override val layoutRes: Int
        get() = R.layout.activity_time_travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        backBtnClickEvent()
    }

    private fun initAdapter() {
        tapeAdapter = TapeAdapter()
        binding.rvTimeTravel.adapter = tapeAdapter
        binding.rvTimeTravel.addItemDecoration(
            TapeItemDecoration(
                23,
                getColor(R.color.light_blue_e9f1fe)
            )
        )
        tapeAdapter.tapeList.addAll(
            listOf(
                TapesResponse.Tape(
                    "asfjkl1nbbj3j2343284bcjksabfd",
                    "어쩔티비",
                    2022,
                    7,
                    22,
                    "2022.07.09",
                    "이미지URL"
                ),
                TapesResponse.Tape(
                    "asfjkl1nbbj3j2343284bcjksabfd",
                    "어쩔티비",
                    2022,
                    7,
                    22,
                    "2022.07.09",
                    "이미지URL"
                ),
                TapesResponse.Tape(
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

    private fun backBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, TimeTravelActivity::class.java)
    }
}