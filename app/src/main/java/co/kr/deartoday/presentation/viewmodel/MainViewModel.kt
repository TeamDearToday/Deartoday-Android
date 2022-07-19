package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.domain.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _timeTravelCount = MutableLiveData<String>()
    val timeTravelCount: LiveData<String> get() = _timeTravelCount

    fun fetchTimeTravelCount() {
        viewModelScope.launch {
            mainRepository.fetchTimeTravelCount()
                .onSuccess {
                    _timeTravelCount.value = it
                    Timber.d(it)
                }.onFailure {
                    Timber.e(it)
                }
        }
    }
}