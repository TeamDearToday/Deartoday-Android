package co.kr.deartoday.presentation.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.data.model.response.ResponseMessageBox
import co.kr.deartoday.databinding.ItemMessageBoxListBinding
import co.kr.deartoday.util.calculateMaxLines

class MessageBoxAdapter : RecyclerView.Adapter<MessageBoxAdapter.MessageBoxViewHolder>() {
    private val messageBoxList = mutableListOf<ResponseMessageBox>()

    class MessageBoxViewHolder(
        private val binding: ItemMessageBoxListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMessageBox) {
            binding.tvMessageItem.text = data.message
            binding.tvMessageItem.calculateMaxLines(data.message) {
                    if (it >= 0) {
                        binding.tvMessageItem.maxLines = it - 1
                        binding.tvMessageItem.ellipsize = TextUtils.TruncateAt.END
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageBoxViewHolder {
        val binding =
            ItemMessageBoxListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MessageBoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageBoxViewHolder, position: Int) {
        holder.onBind(messageBoxList[position])
    }

    override fun getItemCount(): Int = messageBoxList.size
}