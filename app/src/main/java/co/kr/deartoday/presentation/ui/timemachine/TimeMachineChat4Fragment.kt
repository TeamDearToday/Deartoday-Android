package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat4Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import com.bumptech.glide.Glide

class TimeMachineChat4Fragment : BaseFragment<FragmentTimeMachineChat4Binding>() {
    override val TAG: String
        get() = TimeMachineChat4Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat4

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initView()
    }

    private fun initView() {
        Glide.with(binding.ivImage)
            .load(viewModel.imageUri.value)
            .into(binding.ivImage)
    }

    companion object {
        fun newInstance() = TimeMachineChat4Fragment()
    }
}