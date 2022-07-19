package co.kr.deartoday.presentation.usecase

import co.kr.deartoday.data.service.timemachine.TimeMachineService
import co.kr.deartoday.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class TimeMachineUseCase @Inject constructor(
    private val timeMachineService: TimeMachineService,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun postTimeTravel(
        image: MultipartBody.Part,
        data: HashMap<String, RequestBody>
    ): Result<String> {
        return withContext(coroutineDispatcher) {
            runCatching {
                timeMachineService.postTimeTravel(image, data).toEntity()
            }
        }
    }
}