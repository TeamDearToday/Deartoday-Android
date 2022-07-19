package co.kr.deartoday.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import co.kr.deartoday.databinding.ItemTimeTravelBinding

class TapeAdapter(private val itemClick: (TapesResponse.Tape) -> (Unit)) :
    RecyclerView.Adapter<TapeAdapter.TimeTravelTapeViewHolder>() {
    val tapeList = mutableListOf<TapesResponse.Tape>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTravelTapeViewHolder {
        val binding =
            ItemTimeTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeTravelTapeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: TimeTravelTapeViewHolder, position: Int) {
        holder.onBind(tapeList[position])
    }

    override fun getItemCount(): Int = tapeList.size

    class TimeTravelTapeViewHolder(
        private val binding: ItemTimeTravelBinding,
        private val itemClick: (TapesResponse.Tape) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TapesResponse.Tape) {
            binding.content.text = data.title
            binding.tvTravelFrom.text = data.writtenDate
            binding.tvTravelTo.text = "%d.%d.%d".format(data.year, data.month, data.day)
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }
}