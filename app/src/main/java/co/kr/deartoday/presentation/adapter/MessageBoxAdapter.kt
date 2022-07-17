package co.kr.deartoday.presentation.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.data.model.response.MessageBoxResponse
import co.kr.deartoday.databinding.ItemMessageBoxListBinding
import co.kr.deartoday.util.calculateMaxLines

class MessageBoxAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<MessageBoxAdapter.MessageBoxViewHolder>() {
    val messageBoxList = mutableListOf<MessageBoxResponse>()

    class MessageBoxViewHolder(
        private val binding: ItemMessageBoxListBinding,
        private val itemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageBoxResponse) {
            binding.tvMessageItem.text = data.lastAnswers
            binding.tvMessageItem.calculateMaxLines(data.lastAnswers) {
                if (it >= 0) {
                    binding.tvMessageItem.maxLines = it - 1
                    binding.tvMessageItem.ellipsize = TextUtils.TruncateAt.END
                }
            }
            binding.root.setOnClickListener { itemClick(data.lastAnswers) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageBoxViewHolder {
        val binding =
            ItemMessageBoxListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MessageBoxViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MessageBoxViewHolder, position: Int) {
        holder.onBind(messageBoxList[position])
    }

    override fun getItemCount(): Int = messageBoxList.size
}