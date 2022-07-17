package co.kr.deartoday.data.model.response

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)