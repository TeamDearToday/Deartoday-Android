package co.kr.deartoday.data.service

import co.kr.deartoday.data.model.response.ResponseMessageBox
import co.kr.deartoday.data.model.response.TapesResponse
import retrofit2.Call
import retrofit2.http.GET

interface DeartodayService {
    @GET("/timeTravel")
    fun getTapes(): List<TapesResponse>

}