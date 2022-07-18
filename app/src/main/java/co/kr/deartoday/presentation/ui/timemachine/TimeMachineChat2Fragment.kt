package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat2Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.fadeOutAnimator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat2Fragment : BaseFragment<FragmentTimeMachineChat2Binding>() {
    override val TAG: String
        get() = TimeMachineChat2Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat2

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initAnimation()
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            delay(700)
            fadeInAnimator(binding.tvContent, 500).start()
            binding.tvContent.isVisible = true
            fadeOutAnimator(binding.tvContent, 500).start()
            delay(1200)

            parentFragmentManager.commit {
                replace<TimeMachineChat3Fragment>(R.id.fcv_time_machine)
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat2Fragment()
    }
}