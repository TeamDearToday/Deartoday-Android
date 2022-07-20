package co.kr.deartoday.presentation.ui.onboarding

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding10Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeOutAnimator

class Onboarding10Fragment : BaseFragment<FragmentOnboarding10Binding>() {
    override val TAG: String
        get() = Onboarding10Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding10

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tapeLottie()
    }

    private fun tapeLottie() {
        binding.lottieTape.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                val fadeOut1 = fadeOutAnimator(binding.lottieTape, 500)
                fadeOut1.start()
                findNavController().navigate(R.id.action_onboarding10Fragment_to_onboarding11Fragment)
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }
}