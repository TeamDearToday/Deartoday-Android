package co.kr.deartoday.presentation.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import co.kr.deartoday.R
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import co.kr.deartoday.databinding.ActivitySettingBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.onboarding.OnboardingActivity
import co.kr.deartoday.util.shortToast
import timber.log.Timber

class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnClickEvent()
        logoutClickEvent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.not_move,R.anim.right_out)
    }

    private fun backBtnClickEvent() {
        binding.ibSettingBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.not_move, R.anim.right_out)
        }
    }

    private fun logoutClickEvent() {
        binding.tvLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        DearTodaySharedPreferences(this).dearTodayToken = ""
        Timber.v("최유리/토큰 확인[${DearTodaySharedPreferences(this).dearTodayToken}]")
        val intent = Intent(this, OnboardingActivity::class.java)
        ActivityCompat.finishAffinity(this)
        startActivity(intent)
        shortToast("로그아웃 되었습니다")
    }
}