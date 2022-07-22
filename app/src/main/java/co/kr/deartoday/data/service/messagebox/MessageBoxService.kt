package co.kr.deartoday.data.service.messagebox

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.MessageBoxResponse
import retrofit2.http.GET

interface MessageBoxService {
    @GET("/timeTravel/answers")
    suspend fun getMessageBox(): BaseResponse<MessageBoxResponse>
}