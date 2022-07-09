package co.kr.deartoday.presentation.ui.main

import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentMainRightBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment

class MainRightFragment : BaseFragment<FragmentMainRightBinding>() {
    override val TAG: String
        get() = MainRightFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_main_right

    companion object {
        fun newInstance() = MainRightFragment()
    }
}