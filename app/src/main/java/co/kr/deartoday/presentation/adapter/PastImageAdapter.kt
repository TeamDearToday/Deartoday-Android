package co.kr.deartoday.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ItemPastPhotoBinding
import co.kr.deartoday.util.dpToPx
import com.bumptech.glide.Glide

class PastImageAdapter : RecyclerView.Adapter<PastImageAdapter.PastPhotoViewHolder>() {
    private var itemList = listOf<String>()

    class PastPhotoViewHolder(val binding: ItemPastPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(photo: String) {
            Glide.with(binding.ivPhoto)
                .load(photo)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastPhotoViewHolder {
        val binding = DataBindingUtil.inflate<ItemPastPhotoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_past_photo,
            parent,
            false
        )
        val params = binding.root.layoutParams
        params.width = parent.width - (47).dpToPx()
        binding.root.layoutParams = params
        return PastPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PastPhotoViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItem(newItemList: List<String>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}