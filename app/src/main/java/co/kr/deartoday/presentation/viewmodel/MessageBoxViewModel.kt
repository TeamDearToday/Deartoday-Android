package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.service.ServiceCreator.messageBoxService
import co.kr.deartoday.data.model.response.MessageBoxResponse
import co.kr.deartoday.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MessageBoxViewModel : ViewModel() {
    private var _data = MutableLiveData<MessageBoxResponse>() //업데이트
    val data: LiveData<MessageBoxResponse> get() = _data //관찰

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun getMessage() {
        viewModelScope.launch {
            runCatching {
                messageBoxService.getMessageBox()
            }
                .onSuccess {
                    _data = MutableLiveData(it)
                    //성공했을 때 뷰모델 정보 저장
                    _isSuccess.value = true
                }
                .onFailure {
                }
        }
        //isEmpty인 경우에 또 처리해주고?
    }
}