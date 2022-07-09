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
import co.kr.deartoday.presentation.ui.base.BaseFragment

class Onboarding3Fragment : BaseFragment<FragmentOnboarding3Binding>() {
    override val TAG: String
        get() = Onboarding3Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로티 적용 해야함 이건 온보딩 네비 시험용 버튼임
        binding.btnNext3.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding3Fragment_to_onboarding4Fragment)
        }
    }
}