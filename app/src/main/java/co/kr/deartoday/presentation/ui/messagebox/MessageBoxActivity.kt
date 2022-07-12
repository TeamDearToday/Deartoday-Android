package co.kr.deartoday.presentation.ui.messagebox

import android.content.Intent
import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityMessageBoxBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.main.MainActivity

class MessageBoxActivity : BaseActivity<ActivityMessageBoxBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_message_box

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickEvent()
    }

    private fun clickEvent(){
        binding.ibMessageBack.setOnClickListener {
            startActivity(Intent(this@MessageBoxActivity, MainActivity::class.java))
        }
    }
}
