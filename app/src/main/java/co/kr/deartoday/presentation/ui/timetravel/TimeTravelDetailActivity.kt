package co.kr.deartoday.presentation.ui.timetravel

import android.os.Bundle
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityTimeTravelDetailBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.viewmodel.timetravel.TimeTravelDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TimeTravelDetailActivity : BaseActivity<ActivityTimeTravelDetailBinding>() {
    private val viewModel by viewModels<TimeTravelDetailViewModel>()
    override val layoutRes: Int
        get() = R.layout.activity_time_travel_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("putextra 확인 : ${intent.getStringExtra("tape_id")}")

        initBackBtnClickEvent()
        addObserve()
        setTapeId()
        setStickyScrollView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.not_move,R.anim.right_out)
    }

    private fun setStickyScrollView() {
        binding.svSticky.run {
            this.header = binding.clTitleBundle
        }
    }

    private fun setTapeId() {
        if (intent.hasExtra("tape_id")) {
            viewModel.setTapeId(intent.getStringExtra("tape_id").toString())
        } else {
            Timber.e("tape id 값이 넘어오지 않았습니다.")
        }
    }

    private fun addObserve() {
        viewModel.tapeId.observe(this) {
            viewModel.getTape()
        }
        viewModel.tapeData.observe(this) { tapeData ->
            binding.tvTravelTo.text = "%d.%d.%d".format(tapeData.year, tapeData.month, tapeData.day)
            binding.tvTitle.text = tapeData.title
            binding.tvTravelFrom.text = tapeData.writtenDate
            binding.ivOriginal.clipToOutline = true
            Glide.with(this).load(tapeData.image).into(binding.ivOriginal)
            binding.tvDialog01.text = tapeData.messages[0].question
            binding.tvDialogSelf01.text = tapeData.messages[0].answer
            binding.tvDialog02.text = tapeData.messages[1].question
            binding.tvDialogSelf02.text = tapeData.messages[1].answer
            binding.tvDialog03.text = tapeData.messages[2].question
            binding.tvDialogSelf03.text = tapeData.messages[2].answer
            binding.tvDialog04.text = tapeData.messages[3].question
            binding.tvDialogSelf04.text = tapeData.messages[3].answer
            binding.tvDialog05.text = tapeData.messages[4].question
            binding.tvDialogSelf05.text = tapeData.messages[4].answer
            binding.tvDialog06.text = tapeData.messages[5].question
            binding.tvDialogSelf06.text = tapeData.messages[5].answer
            binding.tvPast01.text = "과거의 당신에게 꼭 해주고 싶은 말"
            binding.tvPast02.text = tapeData.messages[6].answer
        }
    }

    private fun initBackBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.not_move,R.anim.right_out)
        }
    }
}