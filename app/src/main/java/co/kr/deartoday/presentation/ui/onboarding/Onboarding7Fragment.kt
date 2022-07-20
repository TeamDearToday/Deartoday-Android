package co.kr.deartoday.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentOnboarding7Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding7Fragment : BaseFragment<FragmentOnboarding7Binding>() {
    override val TAG: String
        get() = Onboarding7Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding7

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoNext7.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding7Fragment_to_onboarding8Fragment)
        }
        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)

            val fadeIn1 = fadeInAnimator(binding.tvOb7Line1, 600)
            binding.tvOb7Line1.visibility = View.VISIBLE
            fadeIn1.start()

            delay(2400)

            val fadeIn2 = fadeInAnimator(binding.tvOb7Line2, 600)
            binding.tvOb7Line2.visibility = View.VISIBLE
            fadeIn2.start()

            delay(2400)

            val fadeIn3 = fadeInAnimator(binding.btnGoNext7, 300)
            binding.btnGoNext7.visibility = View.VISIBLE
            fadeIn3.start()

            binding.btnGoNext7.isClickable = true
        }
    }
}