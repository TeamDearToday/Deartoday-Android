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
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.util.*
import co.kr.deartoday.util.MySoundPlayer.initSounds
import co.kr.deartoday.util.MySoundPlayer.play
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Onboarding2Fragment : BaseFragment<FragmentOnboarding2Binding>() {
    override val TAG: String
        get() = Onboarding2Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAnimation()
        initTouchArea()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            val fadeIn1 = fadeInAnimator(binding.tvOb2Line1, 1000)
            val slideUp1 = slideUpAnimator(binding.tvOb2Line1, 1000)
            binding.tvOb2Line1.visibility = View.VISIBLE
            fadeIn1.start()
            slideUp1.start()

            binding.layoutTouchBox.isClickable = true
            binding.btnCircleTouch2.isClickable = true

            initSounds(requireContext())
            binding.layoutTouchBox.setOnClickListener { v: View? ->
                play(MySoundPlayer.SOUND_BOX)
                findNavController().navigate(R.id.action_onboarding2Fragment_to_onboarding3Fragment)
            }

            binding.btnCircleTouch2.setOnClickListener { v: View? ->
                play(MySoundPlayer.SOUND_BOX)
                findNavController().navigate(R.id.action_onboarding2Fragment_to_onboarding3Fragment)
            }
        }
    }

    private fun initTouchArea() {
        setTouchLayout(
            binding.layoutRoot,
            R.id.layout_touch_box,
            238,
            getScreenWidthDp(),
            199,
            getScreenHeightDp(),
            66,
            56,
            249,
            220
        )
    }
}