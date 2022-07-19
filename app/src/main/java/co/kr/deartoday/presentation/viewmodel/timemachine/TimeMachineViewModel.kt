package co.kr.deartoday.presentation.viewmodel.timemachine

import android.net.Uri
import androidx.lifecycle.*
import co.kr.deartoday.domain.repository.timemachine.TimeMachineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TimeMachineViewModel @Inject constructor(
    private val timeMachineRepository: TimeMachineRepository
) : ViewModel() {
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

    // TimaMachinePastRoomFragment
    private val _pastImages = MutableLiveData<List<String>>()
    val pastImages: LiveData<List<String>> get() = _pastImages

    // TimeMachineChatFragments
    private var _questions = listOf<String>()
    val questions get() = _questions
    private val answers = mutableListOf<String>()
    private var _lastMessages = listOf<String>()
    val lastMessages get() = _lastMessages
    val answer = MutableLiveData<String>()

    init {
        with(_isImagePickerProcessComplete) {
            addSource(imageUri) { checkImagePickerProcessComplete() }
            addSource(date) { checkImagePickerProcessComplete() }
            addSource(title) { checkImagePickerProcessComplete() }
        }
    }

    // TimeMachineImagePickerFragment
    private fun checkImagePickerProcessComplete() {
        _isImagePickerProcessComplete.value =
            (imageUri.value != null) && (date.value != null) && (title.value != null && title.value != "")
    }

    // TimeMachineImagePickerFragment
    fun parseDate() {
        val dateTokenized = requireNotNull(date.value).split('.')
        _year = dateTokenized[0]
        _month = dateTokenized[1]
        _day = dateTokenized[2]
    }

    // TimeMachinePastRoomFragment
    fun fetchPastImages() {
        viewModelScope.launch {
            timeMachineRepository.fetchPastImages(year.toInt())
                .onSuccess {
                    _pastImages.value = it
                }.onFailure {
                    Timber.e(it)
                }
        }
    }

    // TimeMachineChatFragments
    fun fetchQuestions() {
        viewModelScope.launch {
            timeMachineRepository.fetchQuestions()
                .onSuccess {
                    _questions = it.questions
                    _lastMessages = it.lastMessages
                }.onFailure {
                    Timber.e(it)
                }
        }
    }

    fun addAnswer(answer: String) {
        answers.add(answer)
    }
}