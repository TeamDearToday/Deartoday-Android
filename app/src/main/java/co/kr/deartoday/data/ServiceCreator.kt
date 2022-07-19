package co.kr.deartoday.data

import co.kr.deartoday.data.service.AuthService
import co.kr.deartoday.data.service.TapeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import co.kr.deartoday.data.service.MessageBoxService
import co.kr.deartoday.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tapeService: TapeService = retrofit.create(TapeService::class.java)
    val authService: AuthService = retrofit.create(AuthService::class.java)
    val messageBoxService: MessageBoxService = retrofit.create(MessageBoxService::class.java)
}