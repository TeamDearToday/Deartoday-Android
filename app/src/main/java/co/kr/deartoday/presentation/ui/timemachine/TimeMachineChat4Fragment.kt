package co.kr.deartoday.presentation.ui.timemachine

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat4Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.fadeOutAnimator
import co.kr.deartoday.util.shortToast
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat4Fragment : BaseFragment<FragmentTimeMachineChat4Binding>() {
    override val TAG: String
        get() = TimeMachineChat4Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat4

    private val viewModel by activityViewModels<TimeMachineViewModel>()
    private var qnaIndex = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initView()
        initOnClickListener()
        initAnimation()
    }

    private fun initView() {
        binding.svBackground.setOnTouchListener { view, motionEvent -> true }
        Glide.with(binding.ivImage)
            .load(viewModel.imageUri.value)
            .into(binding.ivImage)
    }

    private fun initOnClickListener() {
        binding.tvSend.setOnClickListener {
            sendAnswer()
        }
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            fadeOutAnimator(binding.tvContent, 500).start()
            delay(1000)

            showQuestion()
        }
    }

    private fun showQuestion() {
        if (qnaIndex == viewModel.questions.size) {
            with(binding) {
                tvContent.text = "마지막으로,\n과거의 당신에게 꼭 해주고 싶은 말을 남겨주세요"
//                viewModel.addQuestion(tvContent.text.toString())
                tvContent.gravity = Gravity.CENTER
                tvContent.setBackgroundResource(0)
                tvContent.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white_ffffff
                    )
                )
                tvContent.backgroundTintList = null
                tvContent.maxLines = Integer.MAX_VALUE
            }
        } else {
            (requireActivity() as TimeMachineActivity).mainScope.launch {
                with(binding) {
                    tvContent.text = viewModel.questions[qnaIndex]
                    tvContent.setBackgroundResource(R.drawable.rectangle_textfield_border_yellow_radius_4_20)
                    tvContent.backgroundTintList =
                        requireContext().getColorStateList(R.color.yellow_feffdb)
                    tvContent.maxLines = Integer.MAX_VALUE
                    layoutAnswer.visibility = View.VISIBLE
                }
                fadeInAnimator(binding.tvContent, 500).start()
                fadeInAnimator(binding.etAnswer, 500).start()
                delay(500)
            }
        }
    }

    private fun sendAnswer() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            fadeOutAnimator(binding.etAnswer, 500).start()
            fadeOutAnimator(binding.tvContent, 500).start()
            delay(500)

            if (qnaIndex == viewModel.questions.size) {
                with(binding) {
                    tvContent.text = etAnswer.text.toString()
                    tvContent.setBackgroundResource(R.drawable.rectangle_textfield_border_blue_radius_4_20)
                    tvContent.backgroundTintList =
                        requireContext().getColorStateList(R.color.light_blue_e9f1fe)
                    tvContent.gravity = Gravity.NO_GRAVITY
                    tvContent.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_gray_323232
                        )
                    )
                    tvContent.maxLines = 2
                    tvContent.ellipsize = TextUtils.TruncateAt.END
                    viewModel.addAnswer(etAnswer.text.toString())

                    fadeInAnimator(binding.tvContent, 500).start()
                    fadeOutAnimator(binding.layoutAnswer, 500).start()

                    binding.layoutAnswer.visibility = View.GONE

                    // keyboard down
                    val imm: InputMethodManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view?.windowToken, 0)

                    delay(1000)

                    // fragment change
                    parentFragmentManager.commit {
                        replace<TimeMachineChat5Fragment>(R.id.fcv_time_machine)
                    }
                }
            } else {
                with(binding) {
                    tvContent.text = etAnswer.text.toString()
                    tvContent.setBackgroundResource(R.drawable.rectangle_textfield_border_blue_radius_4_20)
                    tvContent.backgroundTintList =
                        requireContext().getColorStateList(R.color.light_blue_e9f1fe)
                    tvContent.maxLines = 2
                    tvContent.ellipsize = TextUtils.TruncateAt.END
                    viewModel.addAnswer(etAnswer.text.toString())
                    etAnswer.setText("")
                }

                fadeInAnimator(binding.etAnswer, 500).start()
                fadeInAnimator(binding.tvContent, 500).start()
                delay(500)

                qnaIndex++
                showQuestion()
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat4Fragment()
    }
}