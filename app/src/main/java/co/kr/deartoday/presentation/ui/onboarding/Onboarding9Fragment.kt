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

class Onboarding9Fragment : BaseFragment<FragmentOnboarding9Binding>() {
    override val TAG: String
        get() = Onboarding9Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding9

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutPlayer.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding9Fragment_to_onboarding10Fragment)
        }
    }
}