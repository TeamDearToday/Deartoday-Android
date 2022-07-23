package co.kr.deartoday.data.service.auth

import co.kr.deartoday.data.model.request.auth.LoginRequest
import co.kr.deartoday.data.model.request.auth.LogoutRequest
import co.kr.deartoday.data.model.response.auth.AuthResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/auth/login/{social}")
    suspend fun login(
        @Path("social") social: String,
        @Body body: LoginRequest
    ): AuthResponse

    @PATCH("/auth/logout")
    suspend fun logout(
        @Body body: LogoutRequest
    )
}