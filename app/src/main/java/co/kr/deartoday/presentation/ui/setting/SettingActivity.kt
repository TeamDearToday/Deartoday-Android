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

    private fun backBtnClickEvent() {
        binding.ibSettingBack.setOnClickListener{
            finish()
        }
    }

    private fun logout(){
        binding.tvLogout.setOnClickListener{

        }
    }
}
