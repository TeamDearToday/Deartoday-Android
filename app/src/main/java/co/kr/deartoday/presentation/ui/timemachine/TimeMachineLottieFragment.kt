package co.kr.deartoday.presentation.ui.timemachine

import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineLottieBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment

class TimeMachineLottieFragment : BaseFragment<FragmentTimeMachineLottieBinding>() {
    override val TAG: String
        get() = TimeMachineLottieFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_lottie

    companion object {
        fun newInstance() = TimeMachineLottieFragment()
    }
}