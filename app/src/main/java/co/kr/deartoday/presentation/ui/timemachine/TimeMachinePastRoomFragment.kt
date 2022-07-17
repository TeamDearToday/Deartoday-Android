package co.kr.deartoday.presentation.ui.timemachine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachinePastRoomBinding
import co.kr.deartoday.presentation.adapter.PastPhotoAdapter
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.TimeMachineViewModel
import co.kr.deartoday.util.PastPhotoItemDecoration

class TimeMachinePastRoomFragment : BaseFragment<FragmentTimeMachinePastRoomBinding>() {
    override val TAG: String
        get() = TimeMachinePastRoomFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_past_room

    private val viewModel by activityViewModels<TimeMachineViewModel>()
    private val pastPhotoAdapter by lazy { PastPhotoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        viewModel.getPastPhotos()
        initView()
        initOnClickListener()
        observeData()
    }

    private fun initView() {
        with(binding.rvPastPhoto) {
            adapter = pastPhotoAdapter
            addItemDecoration(PastPhotoItemDecoration(15))
        }
    }

    private fun initOnClickListener() {
        binding.tvNext.setOnClickListener {
            parentFragmentManager.commit {
                replace<TimeMachineChat1Fragment>(R.id.fcv_time_machine)
            }
        }
    }

    private fun observeData() {
        viewModel.pastPhotos.observe(viewLifecycleOwner) {
            pastPhotoAdapter.updateItem(it)
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) = TimeMachinePastRoomFragment()
    }
}