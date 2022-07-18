package co.kr.deartoday.data.model.request.auth

data class AuthRequest(
    val socialToken : String,
    val fcmToken : String
)
