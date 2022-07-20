package co.kr.deartoday.presentation.ui.onboarding

import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityOnboardingBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.util.shortToast

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_onboarding

    private var backPressedTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        shortToast("암온더넥스트레벨.\n코틀린문법지켜.")
        backPressedTime = System.currentTimeMillis()
    }
}
