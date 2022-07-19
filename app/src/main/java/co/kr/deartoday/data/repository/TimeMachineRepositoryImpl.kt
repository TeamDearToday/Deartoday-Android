package co.kr.deartoday.data.repository

import co.kr.deartoday.data.service.TimeMachineService
import co.kr.deartoday.di.IoDispatcher
import co.kr.deartoday.domain.repository.timemachine.TimeMachineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeMachineRepositoryImpl @Inject constructor(
    private val timeMachineService: TimeMachineService,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : TimeMachineRepository {
    override suspend fun fetchPastImages(year: Int): Result<List<String>> {
        return withContext(coroutineDispatcher) {
            runCatching {
                timeMachineService.fetchPastImages(year).data.toEntity()
            }
        }
    }
}