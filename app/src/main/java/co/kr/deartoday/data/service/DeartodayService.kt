package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.MessageBoxResponse
import retrofit2.http.GET

interface DeartodayService {
    @GET("/timeTravel/answers")
    suspend fun getMessageBox(): MessageBoxResponse
}