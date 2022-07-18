package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat3Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat3Fragment : BaseFragment<FragmentTimeMachineChat3Binding>() {
    override val TAG: String
        get() = TimeMachineChat3Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat3

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initAnimation()
        initOnClickListener()
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            delay(800)
            fadeInAnimator(binding.tvContent, 300).start()
            binding.tvContent.isVisible = true
            delay(900)
            fadeInAnimator(binding.tvNext, 600).start()
            binding.tvNext.isVisible = true
        }
    }

    private fun initOnClickListener() {
        binding.ivExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvNext.setOnClickListener {
            parentFragmentManager.commit {
                replace<TimeMachineChat4Fragment>(R.id.fcv_time_machine)
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat3Fragment()
    }
}