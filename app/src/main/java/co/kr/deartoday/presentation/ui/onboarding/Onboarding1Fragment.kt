package co.kr.deartoday.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding1Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.slideUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding1Fragment : BaseFragment<FragmentOnboarding1Binding>() {
    override val TAG: String
        get() = Onboarding1Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext1.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding1Fragment_to_onboarding2Fragment)
        }
        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            val fadeIn1 = fadeInAnimator(binding.tvOb1Line1, 1000)
            val slideUp1 = slideUpAnimator(binding.tvOb1Line1, 1000)
            binding.tvOb1Line1.visibility = View.VISIBLE
            fadeIn1.start()
            slideUp1.start()

            delay(1000)

            val fadeIn2 = fadeInAnimator(binding.tvOb1Line2, 1000)
            val slideUp2 = slideUpAnimator(binding.tvOb1Line2, 1000)
            binding.tvOb1Line2.visibility = View.VISIBLE
            fadeIn2.start()
            slideUp2.start()

            delay(600)

            val fadeIn3 = fadeInAnimator(binding.btnNext1, 600)
            binding.btnNext1.visibility = View.VISIBLE
            fadeIn3.start()

            binding.btnNext1.isClickable = true
        }
    }
}