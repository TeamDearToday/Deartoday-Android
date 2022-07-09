package co.kr.deartoday.presentation.ui.main

import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentMainLeftBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment

class MainLeftFragment : BaseFragment<FragmentMainLeftBinding>() {
    override val TAG: String
        get() = MainLeftFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_main_left

    companion object {
        fun newInstance() = MainLeftFragment()
    }
}