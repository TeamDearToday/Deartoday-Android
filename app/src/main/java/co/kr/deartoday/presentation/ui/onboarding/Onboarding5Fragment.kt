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
import co.kr.deartoday.util.slideUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding5Fragment : BaseFragment<FragmentOnboarding5Binding>() {
    override val TAG: String
        get() = Onboarding5Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding5

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlayer.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding5Fragment_to_onboarding6Fragment)
        }
        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {

            delay(1000)

            val fadeIn1 = fadeInAnimator(binding.tvOb5Line1, 600)
            binding.tvOb5Line1.visibility = View.VISIBLE
            fadeIn1.start()

            val fadeIn2 = fadeInAnimator(binding.tvOb5Line2, 600)
            binding.tvOb5Line2.visibility = View.VISIBLE
            fadeIn2.start()

            delay(2400)

            val fadeIn3 = fadeInAnimator(binding.tvOb5Line3, 600)
            binding.tvOb5Line3.visibility = View.VISIBLE
            fadeIn3.start()

            delay(2400)

            val fadeIn4 = fadeInAnimator(binding.tvOb5Line4, 600)
            binding.tvOb5Line4.visibility = View.VISIBLE
            fadeIn4.start()

            delay(2400)

            val fadeIn5 = fadeInAnimator(binding.tvOb5Line5, 600)
            binding.tvOb5Line5.visibility = View.VISIBLE
            fadeIn5.start()

            delay(2400)

            val fadeIn6 = fadeInAnimator(binding.tvOb5Line6, 600)
            binding.tvOb5Line6.visibility = View.VISIBLE
            fadeIn6.start()

            delay(2400)

            val fadeIn7 = fadeInAnimator(binding.btnPlayer, 300)
            binding.btnPlayer.visibility = View.VISIBLE
            fadeIn7.start()

            binding.btnPlayer.isClickable = true
        }
    }
}