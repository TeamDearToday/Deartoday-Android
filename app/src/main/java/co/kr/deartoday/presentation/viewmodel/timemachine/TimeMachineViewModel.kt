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
    val reactions = arrayOf(
        arrayOf(),
        arrayOf(),
        arrayOf("그런 생각을 했었구나."),
        arrayOf("그래서 이때로 돌아오고 싶었구나.\n정말 잘 왔어!", "있잖아,\n지금까지는 그 당시의 너의 생각을 들려줬었다면,\n이제부터는 지금 이 순간의 너의 생각이 궁금해."),
        arrayOf("오.. 그렇구나!", "혹시 이런 말 들어본 적 있어?\n‘좋았다면 추억, 나빴다면 경험’이라는 말이 있듯이,", "우리가 살아가면서 겪게 되는 어떤 순간이든\n시간이 지나고 다시 생각해보면\n그때는 알지 못했던 새로운 부분을 발견할 수 있는 것 같아."),
        arrayOf("좋은 말이야.\n너에게 마지막으로 궁금한 게 있어."),
        arrayOf("답해줘서 고마워.\n오늘 너랑 이렇게 이야기할 수 있어서 정말 좋았어.", "너가 이때로 다시 한 번 돌아가고 싶다고 생각한 만큼,\n나도 최선을 다해서 나의 오늘을 살아갈게.")
    )

    var bitmap: Bitmap? = null

    private val _isSuccess = MutableLiveData<Boolean>()
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