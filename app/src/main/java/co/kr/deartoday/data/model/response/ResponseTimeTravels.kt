package co.kr.deartoday.data.model.response

/**
 * API 문서 참고
 * BaseResponse 의 Data에 들어갈 timeTravels
 * 인자로는 TimeTravelDetail 데이터 클래스가 들어감
 * BaseResponse<ResponseTimeTravels<ResponseTimeTravelDetail>
 * 과 같이 사용할 수 있음.
 */
data class ResponseTimeTravels<T>(
    val timeTravels: List<T>
)