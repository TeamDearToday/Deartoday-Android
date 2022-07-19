package co.kr.deartoday.data.interceptor

import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: DearTodaySharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = when (val accessToken = sharedPreferences.dearTodayToken) {
            "" -> {
                chain.request()
                    .newBuilder()
                    .build()
            }
            else -> {
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build()
            }
        }
        return chain.proceed(authRequest)
    }
}