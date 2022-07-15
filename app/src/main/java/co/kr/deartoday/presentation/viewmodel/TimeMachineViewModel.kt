package co.kr.deartoday.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeMachineViewModel : ViewModel() {
    // TimeMachineImagePickerFragment
    val imageUri = MutableLiveData<Uri>()
    val date = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    private val _isImagePickerProcessComplete = MediatorLiveData<Boolean>()
    val isImagePickerProcessComplete: LiveData<Boolean> get() = _isImagePickerProcessComplete
    private var _year: String = ""
    val year get() = _year
    private var _month: String = ""
    val month get() = _month
    private var _day: String = ""
    val day get() = _day

    init {
        with(_isImagePickerProcessComplete) {
            addSource(imageUri) { checkImagePickerProcessComplete() }
            addSource(date) { checkImagePickerProcessComplete() }
            addSource(title) { checkImagePickerProcessComplete() }
        }
    }

    private fun checkImagePickerProcessComplete() {
        _isImagePickerProcessComplete.value =
            (imageUri.value != null) && (date.value != null) && (title.value != null && title.value != "")
    }

    fun parseDate() {
        val dateTokenized = requireNotNull(date.value).split('.')
        _year = dateTokenized[0]
        _month = dateTokenized[1]
        _day = dateTokenized[2]
    }
}