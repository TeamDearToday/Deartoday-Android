package co.kr.deartoday.data.model.request.auth

data class LoginRequest(
    val socialToken: String,
    val fcmToken: String
)
