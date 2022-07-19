package co.kr.deartoday.data.service.timemachine

import co.kr.deartoday.data.model.response.BaseResponse
import co.kr.deartoday.data.model.response.MultipartResponse
import co.kr.deartoday.data.model.response.timemachine.PastImagesResponse
import co.kr.deartoday.data.model.response.timemachine.QuestionsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface TimeMachineService {
    @GET("/timeTravel/oldMedia")
    suspend fun fetchPastImages(
        @Query("year") year: Int
    ): BaseResponse<PastImagesResponse>

    @GET("timeTravel/question")
    suspend fun fetchQuestions(): BaseResponse<QuestionsResponse>

    @Multipart
    @POST("timeTravel")
    suspend fun postTimeTravel(
        @Part image: MultipartBody.Part,
        @PartMap data: HashMap<String, RequestBody>
    ): MultipartResponse
}