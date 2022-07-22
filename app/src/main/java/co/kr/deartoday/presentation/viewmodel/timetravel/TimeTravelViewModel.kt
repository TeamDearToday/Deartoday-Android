package co.kr.deartoday.presentation.viewmodel.timetravel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.model.response.timetravel.TapesResponse
import co.kr.deartoday.data.service.tape.TapeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TimeTravelViewModel @Inject constructor(
    private val tapeService: TapeService
) : ViewModel() {
    //TimeTravelActivity
    private var _tapes = MutableLiveData<TapesResponse>() //업데이트,변경
    val tapes: LiveData<TapesResponse> get() = _tapes    //관찰

    fun getTapeData() {
        viewModelScope.launch {
            runCatching {
                tapeService.getTapes()
            }.onSuccess {
                Timber.v("성공?")
                _tapes.value = it.data!!
            }.onFailure {
                Timber.v("tape 가져오기 실패 [${it}]")
            }
        }
    }
}