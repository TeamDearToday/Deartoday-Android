package co.kr.deartoday.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityDeartodaySplashBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
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
            fadeInAnimator(binding.tvSoundon, 1000).start()
            binding.tvSoundon.visibility = View.VISIBLE
            delay(2000)
            val intent = Intent(this@DeartodaySplashActivity, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}