package co.kr.deartoday.data.model.response

data class MultipartResponse(
    val status: Int,
    val message: String
) {
    fun toEntity(): String {
        return this.message
    }
}