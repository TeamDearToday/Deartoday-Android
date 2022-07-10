package co.kr.deartoday.presentation.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.*
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding8Fragment : BaseFragment<FragmentOnboarding8Binding>() {
    override val TAG: String
        get() = Onboarding8Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding8

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoNext8.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding8Fragment_to_onboarding9Fragment)
        }
        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)

            val fadeIn1 = fadeInAnimator(binding.tvOb8Line1, 1200)
            binding.tvOb8Line1.visibility = View.VISIBLE
            fadeIn1.start()

            delay(2400)

            val fadeIn2 = fadeInAnimator(binding.tvOb8Line2, 1200)
            binding.tvOb8Line2.visibility = View.VISIBLE
            fadeIn2.start()

            delay(2400)

            val fadeIn3 = fadeInAnimator(binding.tvOb8Line3, 1200)
            binding.tvOb8Line3.visibility = View.VISIBLE
            fadeIn3.start()

            delay(2400)

            val fadeIn4 = fadeInAnimator(binding.tvOb8Line4, 1200)
            binding.tvOb8Line4.visibility = View.VISIBLE
            fadeIn4.start()

            delay(2400)

            val fadeIn5 = fadeInAnimator(binding.btnGoNext8, 600)
            binding.btnGoNext8.visibility = View.VISIBLE
            fadeIn5.start()

            binding.btnGoNext8.isClickable = true
        }
    }
}