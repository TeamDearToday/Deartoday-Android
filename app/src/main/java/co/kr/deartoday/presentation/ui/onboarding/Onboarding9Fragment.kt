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
import co.kr.deartoday.util.MySoundPlayer
import co.kr.deartoday.util.MySoundPlayer.initSounds
import co.kr.deartoday.util.fadeInAnimator
import co.kr.deartoday.util.slideUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Onboarding9Fragment : BaseFragment<FragmentOnboarding9Binding>() {
    override val TAG: String
        get() = Onboarding9Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding9

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            val fadeIn1 = fadeInAnimator(binding.layoutPlayer, 300)
            binding.layoutPlayer.visibility = View.VISIBLE
            fadeIn1.start()

            binding.layoutPlayer.isClickable = true

            initSounds(requireContext())
            binding.layoutPlayer.setOnClickListener { v: View? ->
                MySoundPlayer.play(MySoundPlayer.SOUND_PLAYER)
                findNavController().navigate(R.id.action_onboarding9Fragment_to_onboarding10Fragment)
            }
        }
    }
}