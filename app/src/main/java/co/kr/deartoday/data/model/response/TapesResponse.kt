package co.kr.deartoday.data.model.response

import com.google.gson.annotations.SerializedName

data class TapesResponse(
    @SerializedName("timeTravels")
    val tapes: List<Tape>
) {
    data class Tape(
        val timeTravelId: String,
        val title: String,
        val year: Int,
        val month: Int,
        val day: Int,
        val writtenDate: String,
        val image: String,
    )
}
