package co.kr.deartoday.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ItemPastPhotoBinding

class PastPhotoAdapter : RecyclerView.Adapter<PastPhotoAdapter.PastPhotoViewHolder>() {
    private var itemList = listOf<String>()

    class PastPhotoViewHolder(val binding: ItemPastPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: String) {
//            Glide.with(binding.ivPhoto)
//                .load(photo)
//                .into(binding.ivPhoto)
            binding.ivPhoto.setImageResource(R.drawable.main_left_withg_a_1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastPhotoViewHolder {
        val binding = DataBindingUtil.inflate<ItemPastPhotoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_past_photo,
            parent,
            false
        )
        return PastPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PastPhotoViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItem(newItemList: List<String>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}