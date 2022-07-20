package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.timetravel.DetailTapeResponse
import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TapeService {
    @GET("/timeTravel")
    suspend fun getTapes(): BaseResponse<TapesResponse>

    @GET("/timeTravel/{timeTravelId}")
    suspend fun getTape(
        @Path("timeTravelId") timeTravelId: String,
    ): BaseResponse<DetailTapeResponse>
}