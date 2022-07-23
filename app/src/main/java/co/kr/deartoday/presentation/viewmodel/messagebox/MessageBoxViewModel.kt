package co.kr.deartoday.presentation.viewmodel.messagebox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.service.messagebox.MessageBoxService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MessageBoxViewModel @Inject constructor(
    private val messageBoxService: MessageBoxService
) : ViewModel() {
    private val _lastAnswer = MutableLiveData<List<String>>() //업데이트
    val lastAnswer: LiveData<List<String>> get() = _lastAnswer //관찰

    fun getMessage() {
        viewModelScope.launch {
            runCatching {
                messageBoxService.getMessageBox()
            }.onSuccess {
                _lastAnswer.value = it.data.lastAnswer
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
