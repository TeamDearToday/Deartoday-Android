package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat5Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.fadeOutAnimator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat5Fragment : BaseFragment<FragmentTimeMachineChat5Binding>() {
    override val TAG: String
        get() = TimeMachineChat5Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat5

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        initOnClickListener()
        initAnimation()
    }

    private fun initOnClickListener() {
        binding.tvNext.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            viewModel.lastMessages.forEachIndexed { index, lastMessage ->
                if (index != 0) {
                    fadeOutAnimator(binding.tvContent, 500).start()
                }
                delay(1000)

                with(binding) {
                    tvContent.text = lastMessage
                    tvContent.visibility = View.VISIBLE
                    if (index == viewModel.lastMessages.size - 1) {
                        tvNext.visibility = View.VISIBLE
                    }
                }

                fadeInAnimator(binding.tvContent, 500).start()
                fadeInAnimator(binding.tvNext, 500).start()
                delay(1000)
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat5Fragment()
    }
}