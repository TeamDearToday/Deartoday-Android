package co.kr.deartoday.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding11Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding11Fragment : BaseFragment<FragmentOnboarding11Binding>() {
    override val TAG: String
        get() = Onboarding11Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding11

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlayer.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding11Fragment_to_mainActivity)
        }
        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)

            val fadeIn1 = fadeInAnimator(binding.tvOb11Line1, 600)
            binding.tvOb11Line1.visibility = View.VISIBLE
            fadeIn1.start()

            delay(1200)

            val fadeIn2 = fadeInAnimator(binding.tvOb11Line2, 600)
            binding.tvOb11Line2.visibility = View.VISIBLE
            fadeIn2.start()

            delay(1200)

            val fadeIn3 = fadeInAnimator(binding.tvOb11Line3, 600)
            binding.tvOb11Line3.visibility = View.VISIBLE
            fadeIn3.start()

            delay(1200)

            val fadeIn4 = fadeInAnimator(binding.tvOb11Line4, 600)
            binding.tvOb11Line4.visibility = View.VISIBLE
            fadeIn4.start()

            val fadeIn5 = fadeInAnimator(binding.tvOb11Line5, 600)
            binding.tvOb11Line5.visibility = View.VISIBLE
            fadeIn5.start()

            delay(1200)

            val fadeIn6 = fadeInAnimator(binding.btnPlayer, 300)
            binding.btnPlayer.visibility = View.VISIBLE
            fadeIn6.start()

            binding.btnPlayer.isClickable = true
        }
    }
}