package co.kr.deartoday.presentation.ui.messagebox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivityMessageBoxBinding
import co.kr.deartoday.presentation.adapter.MessageBoxAdapter
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.timemachine.TimeMachineActivity
import co.kr.deartoday.presentation.viewmodel.MessageBoxViewModel
import co.kr.deartoday.util.CustomDialog
import co.kr.deartoday.util.MessageBoxItemDecoration

class MessageBoxActivity : BaseActivity<ActivityMessageBoxBinding>() {
    private lateinit var messageBoxAdapter: MessageBoxAdapter
    private val viewModel by viewModels<MessageBoxViewModel>()
    override val layoutRes: Int
        get() = R.layout.activity_message_box

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = viewModel
        viewModel.getMessage()

        backBtnClickEvent()
        timeTravelBtnClickEvent()
        initAdapter()
        messageBoxItemDecoration()
        observeData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.not_move,R.anim.right_out)
    }

    private fun backBtnClickEvent() {
        binding.ibMessageBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.not_move,R.anim.right_out)
        }
    }

    private fun timeTravelBtnClickEvent() {
        binding.layoutGoTimeTravel.setOnClickListener {
            startActivity(Intent(this@MessageBoxActivity, TimeMachineActivity::class.java))
            finish()
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
    }

    private fun messageBoxItemDecoration() {
        binding.rvMessage.addItemDecoration(
            MessageBoxItemDecoration(4)
        )
    }

    private fun observeData() {
        viewModel.lastAnswer.observe(this) {
            messageBoxAdapter.updateItem(it)
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MessageBoxActivity::class.java)
    }
}
