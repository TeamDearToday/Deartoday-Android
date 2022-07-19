package co.kr.deartoday.domain.repository.timemachine

interface TimeMachineRepository {
    suspend fun fetchPastImages(year: Int): Result<List<String>>
}