package co.kr.deartoday.data.model.response

data class ResponseTimeTravelTape(
    val timeTravelId : String,
    val title : String,
    val year : Int,
    val month : Int,
    val day : Int,
    val writtenDate : String,
    val image : String
)
