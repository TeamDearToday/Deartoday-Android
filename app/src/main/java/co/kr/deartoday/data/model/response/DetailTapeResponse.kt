package co.kr.deartoday.data.model.response

data class DetailTapeResponse(
    val title: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val writtenDate: String,
    val image: String,
    val messages: List<Message>
) {
    data class Message(
        val question: String,
        val answer: String
    )
}