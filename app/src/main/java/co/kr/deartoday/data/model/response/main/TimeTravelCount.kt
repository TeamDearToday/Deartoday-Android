package co.kr.deartoday.data.model.response.main

data class TimeTravelCount(
    val timeTravelCount: Int
) {
    fun toEntity(): String {
        return if (this.timeTravelCount > 99) {
            "99+"
        } else {
            this.timeTravelCount.toString()
        }
    }
}
