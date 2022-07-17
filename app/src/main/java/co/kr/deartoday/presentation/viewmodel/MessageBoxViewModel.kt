package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageBoxViewModel : ViewModel() {
    var lastAnswers = MutableLiveData<String>()
}