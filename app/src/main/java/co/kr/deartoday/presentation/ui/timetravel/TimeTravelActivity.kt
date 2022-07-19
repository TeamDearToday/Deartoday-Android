package co.kr.deartoday.presentation.ui.timetravel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import co.kr.deartoday.databinding.ActivityTimeTravelBinding
import co.kr.deartoday.presentation.adapter.TapeAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.viewmodel.TimeTravelViewModel
import co.kr.deartoday.util.TapeItemDecoration

class TimeTravelActivity : BaseActivity<ActivityTimeTravelBinding>() {
    private val viewModel by viewModels<TimeTravelViewModel>()
    private lateinit var tapeAdapter: TapeAdapter
    override val layoutRes: Int
        get() = R.layout.activity_time_travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        observeUpDate()
        backBtnClickEvent()
        getAdapterData()
    }

    private fun initAdapter() {
        tapeAdapter = TapeAdapter { tape ->
            val intent = Intent(this, TimeTravelDetailActivity::class.java)
            intent.putExtra("tape_id", tape.timeTravelId)  //TODO 키값 수정
            startActivity(intent)
        }
        binding.rvTimeTravel.adapter = tapeAdapter
        binding.rvTimeTravel.addItemDecoration(
            TapeItemDecoration(
                23,
                getColor(R.color.light_blue_e9f1fe)
            )
        )
        tapeAdapter.notifyDataSetChanged()
    }

    private fun getAdapterData() {
        //수정요망
        viewModel.getTapeData(DearTodaySharedPreferences(this).accessToken)
    }

    private fun backBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            finish()
        }
    }

    private fun observeUpDate() {
        viewModel.tapes.observe(this) {
            tapeAdapter.tapeList.addAll(it.tapes)
            tapeAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, TimeTravelActivity::class.java)
    }
}