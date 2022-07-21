package co.kr.deartoday.presentation.ui.timemachine

import android.graphics.ImageDecoder
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
import co.kr.deartoday.util.setOnSingleClickListener
import co.kr.deartoday.util.shortToast
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
        observeData()
    }

    private fun initOnClickListener() {
        binding.ivExit.setOnSingleClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvNext.setOnSingleClickListener {
            (requireActivity() as TimeMachineActivity).mainScope.launch {
                fadeOutAnimator(binding.root, 1000).start()
                delay(2000)
                toBitmap()
//                requireActivity().finish()
            }
        }
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            viewModel.lastMessages.forEachIndexed { index, lastMessage ->
                if (index == 0) {
                    delay(1200)
                    binding.tvContent.text = "소중한 말 남겨줘서 정말 고마워"
                    binding.tvContent.isVisible = true
                    fadeInAnimator(binding.tvContent, 1000).start()
                    fadeOutAnimator(binding.tvContent, 1000).start()
                    delay(4500)
                }

                with(binding) {
                    tvContent.text = lastMessage
                    tvContent.isVisible = true
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
                delay(4500)
            }
        }
    }

    private fun toBitmap() {
        runCatching {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    requireActivity().contentResolver,
                    requireNotNull(viewModel.imageUri.value)
                )
            )
        }.onSuccess {
            viewModel.bitmap = it
            viewModel.postTimeTravel()
        }.onFailure {
            requireContext().shortToast("앗뿔싸!")
        }
    }

    private fun observeData() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if(it || !it) {
                requireActivity().finish()
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat5Fragment()
    }
}