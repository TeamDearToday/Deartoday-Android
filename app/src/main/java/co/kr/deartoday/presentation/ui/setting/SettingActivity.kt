package co.kr.deartoday.presentation.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivitySettingBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.splash.DeartodaySplashActivity
import co.kr.deartoday.presentation.viewmodel.setting.SettingViewModel
import co.kr.deartoday.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_setting

    private val viewModel by viewModels<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnClickEvent()
        logoutClickEvent()
        observeData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.not_move, R.anim.right_out)
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
        viewModel.logout()
    }

    private fun observeData() {
        viewModel.isLogoutSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, DeartodaySplashActivity::class.java))
                ActivityCompat.finishAffinity(this)
                shortToast("로그아웃 되었습니다")
            }
        }
    }
}