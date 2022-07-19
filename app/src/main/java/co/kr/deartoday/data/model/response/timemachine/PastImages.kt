package co.kr.deartoday.data.model.response.timemachine

data class PastImages(
    val images: List<String>
) {
    fun toEntity(): List<String> {
        return this.images
    }
}
