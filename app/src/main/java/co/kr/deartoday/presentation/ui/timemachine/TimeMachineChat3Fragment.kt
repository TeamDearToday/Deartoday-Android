package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineChat3Binding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel

class TimeMachineChat3Fragment : BaseFragment<FragmentTimeMachineChat3Binding>() {
    override val TAG: String
        get() = TimeMachineChat3Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_chat3

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        initOnClickListener()
    }

    private fun initOnClickListener() {
        binding.ivExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvNext.setOnClickListener {
            parentFragmentManager.commit {
                replace<TimeMachineChat4Fragment>(R.id.fcv_time_machine)
            }
        }
    }

    companion object {
        fun newInstance() = TimeMachineChat3Fragment()
    }
}