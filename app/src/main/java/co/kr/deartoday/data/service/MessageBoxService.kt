package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.MessageBoxResponse
import retrofit2.http.GET

interface MessageBoxService {
    @GET("/timeTravel/answers")
    suspend fun getMessageBox(): MessageBoxResponse
}