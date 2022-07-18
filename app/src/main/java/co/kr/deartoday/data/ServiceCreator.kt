package co.kr.deartoday.data

import co.kr.deartoday.data.service.TapeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.209.47.97:8000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tapeService: TapeService = retrofit.create(TapeService::class.java)
}