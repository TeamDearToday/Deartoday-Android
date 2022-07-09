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

class Onboarding4Fragment : BaseFragment<FragmentOnboarding4Binding>() {
    override val TAG: String
        get() = Onboarding4Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding4Fragment_to_onboarding5Fragment)
        }
    }
}