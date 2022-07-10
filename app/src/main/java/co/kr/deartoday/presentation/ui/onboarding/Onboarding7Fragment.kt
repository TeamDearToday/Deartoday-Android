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
    }
}