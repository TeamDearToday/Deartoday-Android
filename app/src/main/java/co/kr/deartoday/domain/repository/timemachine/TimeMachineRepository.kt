package co.kr.deartoday.domain.repository.timemachine

import co.kr.deartoday.domain.entity.timemachine.Questions

interface TimeMachineRepository {
    suspend fun fetchPastImages(year: Int): Result<List<String>>
    suspend fun fetchQuestions(): Result<Questions>
}