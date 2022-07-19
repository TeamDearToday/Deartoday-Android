package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.ServiceCreator
import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import kotlinx.coroutines.launch

class TimeTravelViewModel : ViewModel() {
    //TimeTravelActivity
    private var _tapes = MutableLiveData<TapesResponse>() //업데이트,변경
    val tapes: LiveData<TapesResponse> get() = _tapes    //관찰

    fun getTapeData(token: String) {
        viewModelScope.launch {
            runCatching {
                //TODO 서비스 만들기
                ServiceCreator.tapeService.getTapes()
            }.onSuccess {
                _tapes.value = it.data!!
            }.onFailure {
                //TODO 오류처리
            }
        }
    }
}