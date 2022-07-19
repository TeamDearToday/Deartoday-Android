package co.kr.deartoday.data.model.response.timemachine

data class PastImagesResponse(
    val images: List<String>
) {
    fun toEntity(): List<String> {
        return this.images
    }
}
