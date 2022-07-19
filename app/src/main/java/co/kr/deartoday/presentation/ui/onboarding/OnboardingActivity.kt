package co.kr.deartoday.presentation.ui.onboarding

import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityOnboardingBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_onboarding

    override fun onBackPressed() {
    }
}
