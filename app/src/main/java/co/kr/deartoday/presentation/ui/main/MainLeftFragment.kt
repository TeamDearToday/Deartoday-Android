package co.kr.deartoday.presentation.ui.main

import android.os.Bundle
import android.view.View
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentMainLeftBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.ui.timemachine.TimeMachineActivity
import co.kr.deartoday.util.getScreenHeightDp
import co.kr.deartoday.util.getScreenWidthDp
import co.kr.deartoday.util.setTouchLayout

class MainLeftFragment : BaseFragment<FragmentMainLeftBinding>() {
    override val TAG: String
        get() = MainLeftFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_main_left

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTouchArea()
        initOnClickListener()
    }

    private fun initTouchArea() {
        setTouchLayout(
            binding.layoutRoot,
            R.id.layout_video_player,
            253,
            getScreenWidthDp(),
            240,
            getScreenHeightDp(),
            16,
            91,
            296,
            132
        )
    }

    private fun initOnClickListener() {
        binding.layoutVideoPlayer.setOnClickListener {
            startActivity(TimeMachineActivity.getIntent(requireContext()))
        }
        binding.layoutBtnVideoPlayer.setOnClickListener {
            startActivity(TimeMachineActivity.getIntent(requireContext()))
        }
    }

    companion object {
        fun newInstance() = MainLeftFragment()
    }
}