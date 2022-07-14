package co.kr.deartoday.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.data.model.response.ResponseTimeTravelTape
import co.kr.deartoday.databinding.ItemTimeTravelBinding

class TimeTravelTapeAdapter :
    RecyclerView.Adapter<TimeTravelTapeAdapter.TimeTravelTapeViewHolder>() {
    private val tapeList = mutableListOf<ResponseTimeTravelTape>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTravelTapeViewHolder {
        val binding =
            ItemTimeTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeTravelTapeViewHolder((binding))
    }

    override fun onBindViewHolder(holder: TimeTravelTapeViewHolder, position: Int) {
        holder.onBind(tapeList[position])
    }

    override fun getItemCount(): Int = tapeList.size

    class TimeTravelTapeViewHolder(
        private val binding: ItemTimeTravelBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseTimeTravelTape) {
            binding.apply {
                content.text = data.title
                tvTravelFrom.text = data.writtenDate
                tvTravelTo.text = "%d.%d.%d".format(data.year,data.month,data.day)
            }
        }
    }
}