package co.kr.deartoday.presentation.ui.main

import android.os.Bundle
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
        binding.vpMain.adapter = mainViewPagerAdapter
    }
}