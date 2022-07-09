package co.kr.deartoday.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.kr.deartoday.presentation.ui.main.MainLeftFragment
import co.kr.deartoday.presentation.ui.main.MainRightFragment
import java.lang.IllegalArgumentException

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainLeftFragment.newInstance()
            1 -> MainRightFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }
}