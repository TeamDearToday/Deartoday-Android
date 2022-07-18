package co.kr.deartoday.data.model.response.auth

data class AuthResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: AccessToken
) {
    data class AccessToken(
        val accessToken: String
    )
}
