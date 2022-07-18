package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import retrofit2.http.GET

interface TapeService {
    @GET("/timeTravel")
    fun getTapes(): List<TapesResponse>

}