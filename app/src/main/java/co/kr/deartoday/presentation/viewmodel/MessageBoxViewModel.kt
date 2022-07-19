package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.ServiceCreator
import co.kr.deartoday.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MessageBoxViewModel : ViewModel() {
    private var _data = MutableLiveData<List<String>>() //업데이트
    val data: LiveData<List<String>> get() = _data //관찰

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun getMessage() {
        viewModelScope.launch {
            runCatching {
                ServiceCreator.messageBoxService.getMessageBox()
            }
                .onSuccess {
                    _data.value = it.data
                }
                .onFailure {
                    Timber.e(it)
                }
        }
    }
}