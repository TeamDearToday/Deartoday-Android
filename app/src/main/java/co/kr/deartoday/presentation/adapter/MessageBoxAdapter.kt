package co.kr.deartoday.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.data.model.response.ResponseMessageBox
import co.kr.deartoday.databinding.ItemMessageBoxBinding

class MessageBoxAdapter : RecyclerView.Adapter<MessageBoxAdapter.MessageBoxViewHolder>() {
    val MessageBoxList = mutableListOf<ResponseMessageBox>()

    class MessageBoxViewHolder(
        private val binding: ItemMessageBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMessageBox) {
            binding.tvMessage.text = data.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageBoxViewHolder {
        val binding =
            ItemMessageBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MessageBoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageBoxViewHolder, position: Int) {
        holder.onBind(MessageBoxList[position])
    }

    override fun getItemCount(): Int = MessageBoxList.size
}