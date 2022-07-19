package co.kr.deartoday.data

import co.kr.deartoday.data.service.MessageBoxService
import co.kr.deartoday.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val messageBoxService: MessageBoxService = retrofit.create(MessageBoxService::class.java)
}