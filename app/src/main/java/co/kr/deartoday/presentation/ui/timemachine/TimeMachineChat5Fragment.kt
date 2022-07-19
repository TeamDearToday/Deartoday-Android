package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat5Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.timemachine.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.fadeOutAnimator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
            (requireActivity() as TimeMachineActivity).mainScope.launch {
                fadeOutAnimator(binding.root, 1000).start()
                delay(2000)
                requireActivity().finish()
            }
        }
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            viewModel.lastMessages.forEachIndexed { index, lastMessage ->
                if (index == 0) {
                    delay(1200)
                }

                with(binding) {
                    tvContent.text = lastMessage
                    tvContent.visibility = View.VISIBLE
                }

                fadeInAnimator(binding.tvContent, 1000).start()
                if (index != viewModel.lastMessages.size - 1) {
                    fadeOutAnimator(binding.tvContent, 1000).start()
                } else {
                    fadeInAnimator(binding.tvNext, 1000).start()
                    with(binding.tvNext) {
                        isVisible = true
                        isClickable = true
                    }
                }
                delay(3200)
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat5Fragment()
    }
}