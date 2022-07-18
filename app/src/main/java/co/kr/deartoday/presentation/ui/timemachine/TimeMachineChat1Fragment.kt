package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat1Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat1Fragment : BaseFragment<FragmentTimeMachineChat1Binding>() {
    override val TAG: String
        get() = TimeMachineChat1Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat1

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        viewModel.getQuestions()
        initAnimation()
        initOnClickListener()
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            delay(700)
            fadeInAnimator(binding.tvContent, 500).start()
            binding.tvContent.visibility = View.VISIBLE
            delay(1100)
            fadeInAnimator(binding.tvNext, 600).start()
            binding.tvNext.visibility = View.VISIBLE
            delay(600)
            binding.tvNext.isClickable = true
        }
    }

    private fun initOnClickListener() {
        binding.tvNext.setOnClickListener {
            parentFragmentManager.commit {
                replace<TimeMachineChat2Fragment>(R.id.fcv_time_machine)
            }
        }
        binding.ivExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat1Fragment()
    }
}