package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.main.TimeTravelCount
import retrofit2.http.GET

interface MainService {
    @GET("/timeTravel/count")
    suspend fun fetchTimeTravelCount(): BaseResponse<TimeTravelCount>
}