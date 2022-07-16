package co.kr.deartoday.presentation.ui.messagebox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import co.kr.deartoday.R
import co.kr.deartoday.data.model.response.ResponseMessageBox
import co.kr.deartoday.databinding.ActivityMessageBoxBinding
import co.kr.deartoday.presentation.adapter.MessageBoxAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.main.MainActivity
import co.kr.deartoday.presentation.ui.timemachine.TimeMachineActivity
import co.kr.deartoday.util.CustomDialog
import co.kr.deartoday.util.MessageBoxItemDecoration

class MessageBoxActivity : BaseActivity<ActivityMessageBoxBinding>() {
    private lateinit var messageBoxAdapter: MessageBoxAdapter
    override val layoutRes: Int
        get() = R.layout.activity_message_box

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnClickEvent()
        timeTravelBtnClickEvent()
        initAdapter()
        messageBoxItemDecoration()
    }

    private fun backBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            finish()
        }
    }

    private fun timeTravelBtnClickEvent() {
        binding.layoutGoTimeTravel.setOnClickListener {
            startActivity(Intent(this@MessageBoxActivity, MainActivity::class.java))
        }
    }

    private fun initAdapter() {
        messageBoxAdapter = MessageBoxAdapter { message ->
            val dialog = CustomDialog(this)
            dialog.showConfirmDialog(
                R.layout.dialog_message_box,
                R.drawable.inset_horizontal_80,
                message
            )
        }
        binding.rvMessage.adapter = messageBoxAdapter
        messageBoxAdapter.messageBoxList.addAll(
            listOf(
                ResponseMessageBox("유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? 유리는 하여자다! 아닌데? "),
                ResponseMessageBox("하냥이"),
                ResponseMessageBox("하연자"),
                ResponseMessageBox("하하여자"),
                ResponseMessageBox("디어투데이"),
                ResponseMessageBox("하별찬"),
                ResponseMessageBox("하메피"),
                ResponseMessageBox("하피엠"),
                ResponseMessageBox("하헬창"),
                ResponseMessageBox("하남자"),
                ResponseMessageBox("쌉하남자")
            )
        )

        messageBoxAdapter.notifyDataSetChanged()

    }


    private fun messageBoxItemDecoration() {
        binding.rvMessage.addItemDecoration(
            MessageBoxItemDecoration(4)
        )
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MessageBoxActivity::class.java)
    }
}
