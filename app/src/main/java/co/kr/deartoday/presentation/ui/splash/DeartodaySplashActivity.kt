package co.kr.deartoday.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityDeartodaySplashBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.onboarding.OnboardingActivity
import co.kr.deartoday.util.fadeInAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeartodaySplashActivity : BaseActivity<ActivityDeartodaySplashBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_deartoday_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAnimation()
    }

    private fun initAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            fadeInAnimator(binding.tvSoundon, 500).start()
            binding.tvSoundon.visibility = View.VISIBLE
            delay(3000)
            val intent = Intent(this@DeartodaySplashActivity, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}