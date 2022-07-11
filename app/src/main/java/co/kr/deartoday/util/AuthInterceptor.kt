package co.kr.deartoday.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("name", "value")
            .build()

        return chain.proceed(request)
    }
}