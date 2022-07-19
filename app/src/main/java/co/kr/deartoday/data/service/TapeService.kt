package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import retrofit2.http.GET

interface TapeService {
    @GET("/timeTravel")
    suspend fun getTapes(): BaseResponse<TapesResponse>
}