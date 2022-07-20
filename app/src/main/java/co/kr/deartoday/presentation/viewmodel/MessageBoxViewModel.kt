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

    fun getMessage() {
        viewModelScope.launch {
            runCatching {
                ServiceCreator.messageBoxService.getMessageBox()
            }.onSuccess {
                _data.value = it.data
            //  _data.value = listOf<String>() //이 코드는 서버에 데이터가 담겨있지 않을 때를 시험하는 코드입니다
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
