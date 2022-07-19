package co.kr.deartoday.presentation.ui.onboarding

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
import co.kr.deartoday.databinding.FragmentOnboarding4Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.slideUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding4Fragment : BaseFragment<FragmentOnboarding4Binding>() {
    override val TAG: String
        get() = Onboarding4Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReadLetter.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding4Fragment_to_onboarding5Fragment)
        }

        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            val fadeIn1 = fadeInAnimator(binding.tvOb4Line1, 500)
            val slideUp1 = slideUpAnimator(binding.tvOb4Line1, 500)
            binding.tvOb4Line1.visibility = View.VISIBLE
            fadeIn1.start()
            slideUp1.start()

            delay(1000)

            val fadeIn2 = fadeInAnimator(binding.tvOb4Line2, 500)
            val slideUp2 = slideUpAnimator(binding.tvOb4Line2, 500)
            binding.tvOb4Line2.visibility = View.VISIBLE
            fadeIn2.start()
            slideUp2.start()

            delay(2000)

            val fadeIn3 = fadeInAnimator(binding.tvOb4Line3, 1000)
            val slideUp3= slideUpAnimator(binding.tvOb4Line3, 1000)
            binding.tvOb4Line3.visibility = View.VISIBLE
            fadeIn3.start()
            slideUp3.start()

            delay(600)

            val fadeIn4 = fadeInAnimator(binding.btnReadLetter, 300)
            binding.btnReadLetter.visibility = View.VISIBLE
            fadeIn4.start()

            binding.btnReadLetter.isClickable = true
        }
    }
}