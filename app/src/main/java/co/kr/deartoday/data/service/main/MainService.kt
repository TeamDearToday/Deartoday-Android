package co.kr.deartoday.data.service.main

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.main.TimeTravelCountResponse
import retrofit2.http.GET

interface MainService {
    @GET("/timeTravel/count")
    suspend fun fetchTimeTravelCount(): BaseResponse<TimeTravelCountResponse>
}