package co.kr.deartoday.presentation.ui.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityMainBinding
import co.kr.deartoday.presentation.adapter.MainViewPagerAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity

class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        mainViewPagerAdapter = MainViewPagerAdapter(this)
        with(binding.vpMain) {
            adapter = mainViewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.ivIndicatorOne.setBackgroundResource(R.drawable.circle_full)
                            binding.ivIndicatorTwo.setBackgroundResource(R.drawable.circle_border)
                        }
                        else -> {
                            binding.ivIndicatorOne.setBackgroundResource(R.drawable.circle_border)
                            binding.ivIndicatorTwo.setBackgroundResource(R.drawable.circle_full)
                        }
                    }
                }
            })
        }
    }
}