package co.kr.deartoday.presentation.ui.onboarding

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding1Binding
import co.kr.deartoday.databinding.FragmentOnboarding2Binding
import co.kr.deartoday.databinding.FragmentOnboarding3Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.fadeOutAnimator
import co.kr.deartoday.util.slideUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                    val fadeOut1 = fadeOutAnimator(binding.lottieBox, 1000)
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