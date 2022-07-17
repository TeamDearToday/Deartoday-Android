package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.model.response.TapesResponse
import kotlinx.coroutines.launch

class TimeTravelViewModel : ViewModel() {
    //TimeTravelActivity
    private var _tapes = MutableLiveData<List<TapesResponse.Tape>>() //업데이트,변경
    val tapes : LiveData<List<TapesResponse.Tape>> get() = _tapes    //관찰

    fun setTapeData(){
        viewModelScope.launch {
            runCatching {
                //TODO 서비스 만들기
                //(tapes서비스)
            }.onSuccess {
                //_tapes = it
            }.onFailure {
                //TODO 오류처리
            }
        }
    }
}