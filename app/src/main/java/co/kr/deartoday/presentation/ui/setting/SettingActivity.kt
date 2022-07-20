package co.kr.deartoday.presentation.ui.setting

import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivitySettingBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnClickEvent()
        logout()
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

    private fun logout() {
        binding.tvLogout.setOnClickListener {

        }
    }
}
