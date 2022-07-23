package co.kr.deartoday.presentation.ui.timetravel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityTimeTravelBinding
import co.kr.deartoday.presentation.adapter.TapeAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.timemachine.TimeMachineActivity
import co.kr.deartoday.presentation.viewmodel.timetravel.TimeTravelViewModel
import co.kr.deartoday.util.TapeItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TimeTravelActivity : BaseActivity<ActivityTimeTravelBinding>() {
    private val viewModel by viewModels<TimeTravelViewModel>()
    private lateinit var tapeAdapter: TapeAdapter
    override val layoutRes: Int
        get() = R.layout.activity_time_travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = viewModel
        initAdapter()
        observeUpDate()
        backBtnClickEvent()
        timeTravelBtnClickEvent()
        getAdapterData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.not_move,R.anim.right_out)
    }

    private fun initAdapter() {
        tapeAdapter = TapeAdapter { tapeId ->
            val intent = Intent(this, TimeTravelDetailActivity::class.java)
            Timber.d("tapeId for intent = [${tapeId}]")
            intent.putExtra("tape_id", tapeId)  //TODO 키값 수정
            startActivity(intent)
            overridePendingTransition(R.anim.right_in,R.anim.not_move)
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
        viewModel.getTapeData()
    }

    private fun backBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.not_move,R.anim.right_out)
        }
    }

    private fun observeUpDate() {
        viewModel.tapes.observe(this) {
            tapeAdapter.tapeList.addAll(it.tapes)
            tapeAdapter.notifyDataSetChanged()
        }
    }

    private fun timeTravelBtnClickEvent() {
        binding.layoutGoTimeTravel.setOnClickListener {
            startActivity(Intent(this, TimeMachineActivity::class.java))
            finish()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, TimeTravelActivity::class.java)
    }
}