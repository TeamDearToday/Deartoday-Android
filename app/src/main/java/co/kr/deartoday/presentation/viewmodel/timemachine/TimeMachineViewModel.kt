package co.kr.deartoday.presentation.viewmodel.timemachine

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.lifecycle.*
import co.kr.deartoday.domain.repository.timemachine.TimeMachineRepository
import co.kr.deartoday.presentation.usecase.TimeMachineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TimeMachineViewModel @Inject constructor(
    private val timeMachineRepository: TimeMachineRepository,
    private val timeMachineUseCase: TimeMachineUseCase
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

    var bitmap: Bitmap? = null

    val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

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

    fun postTimeTravel() {
        // title: String,
        // year, month, day: Int,
        // image: MultipartBody.Part
        // writtenDate: String
        // questions[0] ~ questions[6]: String
        // answers[0] ~ answers[6]: String
        val requestBodyHashMap = HashMap<String, RequestBody>()
        val titleRequestBody =
            requireNotNull(title.value).toRequestBody("text/plain".toMediaTypeOrNull())
        val yearRequestBody = year.toRequestBody("text/plain".toMediaTypeOrNull())
        val monthRequestBody = month.toRequestBody("text/plain".toMediaTypeOrNull())
        val dayRequestBody = day.toRequestBody("text/plain".toMediaTypeOrNull())
        val writtenDateRequestBody =
            requireNotNull(date.value).toRequestBody("text/plain".toMediaTypeOrNull())
        requestBodyHashMap["title"] = titleRequestBody
        requestBodyHashMap["year"] = yearRequestBody
        requestBodyHashMap["month"] = monthRequestBody
        requestBodyHashMap["day"] = dayRequestBody
        requestBodyHashMap["writtenDate"] = writtenDateRequestBody

        for (i in 0..6) {
            val question = if (i == 6) {
                "마지막으로, 과거의 당신에게 꼭 해주고 싶은 말을 남기세요."
            } else {
                questions[i]
            }
            val answer = answers[i]
            val questionRequestBody = question.toRequestBody("text/plain".toMediaTypeOrNull())
            val answerRequestBody = answer.toRequestBody("text/plain".toMediaTypeOrNull())
            requestBodyHashMap["questions[$i]"] = questionRequestBody
            requestBodyHashMap["answers[$i]"] = answerRequestBody
        }


        val imageRequestBody = BitmapRequestBody(requireNotNull(bitmap))
        val imageMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "image",
                "and" + System.currentTimeMillis().toString(),
                imageRequestBody
            )

        viewModelScope.launch {
            timeMachineUseCase.postTimeTravel(imageMultipartBody, requestBodyHashMap)
                .onSuccess {
                    _isSuccess.value = true
                }.onFailure {
                    _isSuccess.value = false
                    Timber.e(it)
                }
        }
    }

    fun addAnswer(answer: String) {
        answers.add(answer)
    }

    companion object {
        class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
            override fun contentType(): MediaType? {
                return "image/png".toMediaTypeOrNull()
            }

            override fun writeTo(sink: BufferedSink) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 99, sink.outputStream()) //99프로 압축
            }
        }
    }
}