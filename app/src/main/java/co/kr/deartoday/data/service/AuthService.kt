package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.request.auth.AuthRequest
import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.auth.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/auth/login/:social")
    suspend fun getAccessToken(
        @Path("social") social: String,
        @Body body: AuthRequest
    ): BaseResponse<AuthResponse>
}