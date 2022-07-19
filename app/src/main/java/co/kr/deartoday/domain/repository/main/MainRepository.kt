package co.kr.deartoday.domain.repository.main

interface MainRepository {
    suspend fun fetchTimeTravelCount(): Result<String>
}