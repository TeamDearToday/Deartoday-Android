package co.kr.deartoday.data.service.timemachine

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.timemachine.PastImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeMachineService {
    @GET("/timeTravel/oldMedia")
    suspend fun fetchPastImages(
        @Query("year") year: Int
    ): BaseResponse<PastImagesResponse>
}