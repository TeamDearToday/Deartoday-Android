package co.kr.deartoday.presentation.viewmodel.timetravel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.model.response.timetravel.DetailTapeResponse
import co.kr.deartoday.data.service.tape.TapeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TimeTravelDetailViewModel @Inject constructor(
    private val tapeService: TapeService
) : ViewModel() {
    private var _tapeId = MutableLiveData<String>() //업데이트,변경
    val tapeId: LiveData<String> get() = _tapeId    //관찰

    private var _tapeData = MutableLiveData<DetailTapeResponse>() //업데이트,변경
    val tapeData: LiveData<DetailTapeResponse> get() = _tapeData    //관찰

    fun getTape() {
        viewModelScope.launch {
            runCatching {
                tapeService.getTape(
                    _tapeId.value ?: error("아 어차피 앞에서 짤린다고 ㄹㅇㅋㅋ 뒤에서도 짤릴꺼고")
                )
            }.onSuccess {
                _tapeData.value = it.data!!
                Timber.d("최우형2 - detail 서버통신 성공", "성공?")
            }.onFailure {
                Timber.d("최우형1 tape 가져오기 실패 [${it}]")
            }
        }
    }

    fun setTapeId(id: String) {
        _tapeId.value = id
    }
}