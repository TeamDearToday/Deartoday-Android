package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineLottieBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.getTodayString
import co.kr.deartoday.util.textCounterAnimator

class TimeMachineLottieFragment : BaseFragment<FragmentTimeMachineLottieBinding>() {
    override val TAG: String
        get() = TimeMachineLottieFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_lottie

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        startDateAnimation()
    }

    private fun startDateAnimation() {
        val today = getTodayString()
        val past = requireNotNull(viewModel.date.value)
        val todayTokenized = today.split('.')
        val pastTokenized = past.split('.')
        textCounterAnimator(binding.tvYear, todayTokenized[0], pastTokenized[0], 3000).start()
        textCounterAnimator(binding.tvMonth, todayTokenized[1], pastTokenized[1], 3000).start()
        textCounterAnimator(binding.tvDay, todayTokenized[2], pastTokenized[2], 3000).start()
    }

    companion object {
        fun newInstance() = TimeMachineLottieFragment()
    }
}