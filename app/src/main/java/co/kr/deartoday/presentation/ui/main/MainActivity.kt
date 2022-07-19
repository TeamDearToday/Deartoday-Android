package co.kr.deartoday.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityMainBinding
import co.kr.deartoday.presentation.adapter.MainViewPagerAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.onboarding.OnboardingActivity
import co.kr.deartoday.presentation.ui.setting.SettingActivity
import co.kr.deartoday.presentation.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTimeTravelCount()
        initView()
        settingBtnClickEvent()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchTimeTravelCount()
    }

    private fun initView() {
        initViewPager()
        initDateView()
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

    private fun initDateView() {
        val todayFormat = SimpleDateFormat("yyyyMMdd")
        val today = todayFormat.format(Date())
        with(binding) {
            tvTodayYear.text = today.substring(0, 4)
            tvTodayMonth.text = today.substring(4, 6)
            tvTodayDay.text = today.substring(6, 8)
        }
    }

    private fun settingBtnClickEvent(){
        binding.ivSetting.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}