package co.kr.deartoday.presentation.ui.onboarding

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding3Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeOutAnimator

class Onboarding3Fragment : BaseFragment<FragmentOnboarding3Binding>() {
    override val TAG: String
        get() = Onboarding3Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boxLottie()
    }

    private fun boxLottie() {
        binding.lottieBox.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                val fadeOut1 = fadeOutAnimator(binding.lottieBox, 500)
                fadeOut1.start()
                findNavController().navigate(R.id.action_onboarding3Fragment_to_onboarding4Fragment)
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }
}