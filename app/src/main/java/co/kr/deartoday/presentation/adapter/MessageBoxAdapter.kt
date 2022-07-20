package co.kr.deartoday.presentation.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.databinding.ItemMessageBoxListBinding
import co.kr.deartoday.util.calculateMaxLines

class MessageBoxAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<MessageBoxAdapter.MessageBoxViewHolder>() {
    private var messageBoxList = listOf<String>()

    class MessageBoxViewHolder(
        private val binding: ItemMessageBoxListBinding,
        private val itemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(msg:String) {
            binding.message = msg
            binding.tvMessageItem.calculateMaxLines(msg) {
                if (it >= 0) {
                    binding.tvMessageItem.maxLines = it - 1
                    binding.tvMessageItem.ellipsize = TextUtils.TruncateAt.END
                }
            }
            binding.root.setOnClickListener { itemClick(msg) }
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

    fun updateItem(list: List<String>) {
        messageBoxList = list
        notifyDataSetChanged()
    }
}