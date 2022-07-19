package co.kr.deartoday.data.model.response.timemachine

import co.kr.deartoday.domain.entity.timemachine.Questions
import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    val questions: List<String>,
    @SerializedName("lastMessage")
    val lastMessages: List<String>
) {
    fun toEntity(): Questions {
        return Questions(
            questions = this.questions,
            lastMessages = this.lastMessages
        )
    }
}
