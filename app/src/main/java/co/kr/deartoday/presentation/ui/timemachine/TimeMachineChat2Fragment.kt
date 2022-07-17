package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat2Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeMachineChat2Fragment : BaseFragment<FragmentTimeMachineChat2Binding>() {
    override val TAG: String
        get() = TODO("Not yet implemented")
    override val layoutRes: Int
        get() = TODO("Not yet implemented")

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initAnimation()
    }

    private fun initAnimation() {
        (requireActivity() as TimeMachineActivity).mainScope.launch {
            delay(700)
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) = TimeMachineChat2Fragment()
    }
}