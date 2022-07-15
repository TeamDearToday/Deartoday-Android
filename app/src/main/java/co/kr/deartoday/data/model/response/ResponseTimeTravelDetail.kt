package co.kr.deartoday.data.model.response

/**
 * 1.timeTravels 객체에 들어가는 배열의 요소의 형태
 * 이 경우 messages 는 비어있음
 * 2.상세조회에서는 Data 에 바로 들어감.
 * 이 경우에는 timeTravelId 는 비어있음
 */
data class ResponseTimeTravelDetail(
    val timeTravelId : String,
    val title : String,
    val year : Int,
    val month : Int,
    val day : Int,
    val writtenDate : String,
    val image : String,
    val messages : List<Data>
){
    data class Data (
        val question : String,
        val answer : String
        )
}