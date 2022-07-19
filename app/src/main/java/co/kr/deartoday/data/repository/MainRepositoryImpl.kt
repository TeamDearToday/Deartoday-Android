package co.kr.deartoday.data.repository

import co.kr.deartoday.data.service.MainService
import co.kr.deartoday.domain.repository.main.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val coroutineDispatcher: CoroutineDispatcher
) : MainRepository {
    override suspend fun fetchTimeTravelCount(): Result<String> {
        return withContext(coroutineDispatcher) {
            runCatching {
                mainService.fetchTimeTravelCount().data.toEntity()
            }
        }
    }
}