package co.kr.deartoday.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentMainRightBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.ui.messagebox.MessageBoxActivity
import co.kr.deartoday.presentation.ui.timetravel.TimeTravelActivity
import co.kr.deartoday.presentation.viewmodel.main.MainViewModel
import co.kr.deartoday.util.getScreenHeightDp
import co.kr.deartoday.util.getScreenWidthDp
import co.kr.deartoday.util.setTouchLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainRightFragment : BaseFragment<FragmentMainRightBinding>() {
    override val TAG: String
        get() = MainRightFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_main_right

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        initTouchArea()
        initOnClickListener()
    }

    private fun initOnClickListener() {
        binding.layoutMessage.setOnClickListener {
            startActivity(MessageBoxActivity.getIntent(requireContext()))
        }
        binding.layoutBtnMessage.setOnClickListener {
            startActivity(MessageBoxActivity.getIntent(requireContext()))
        }
        binding.layoutVideoTape.setOnClickListener {
            startActivity(TimeTravelActivity.getIntent(requireContext()))
        }
        binding.layoutBtnVideoTape.setOnClickListener {
            startActivity(TimeTravelActivity.getIntent(requireContext()))
        }
    }

    private fun initTouchArea() {
        setTouchLayout(
            binding.layoutRoot,
            R.id.layout_message,
            183,
            getScreenWidthDp(),
            163,
            getScreenHeightDp(),
            158,
            19,
            161,
            344
        )
        setTouchLayout(
            binding.layoutRoot,
            R.id.layout_video_tape,
            305,
            getScreenWidthDp(),
            187,
            getScreenHeightDp(),
            23,
            32,
            444,
            37
        )
    }

    companion object {
        fun newInstance() = MainRightFragment()
    }
}