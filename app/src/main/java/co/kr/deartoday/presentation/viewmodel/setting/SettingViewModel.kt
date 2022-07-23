package co.kr.deartoday.presentation.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.model.request.auth.LogoutRequest
import co.kr.deartoday.data.service.auth.AuthService
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val authService: AuthService,
    private val dearTodaySharedPreferences: DearTodaySharedPreferences
) : ViewModel() {
    private val _isLogoutSuccess = MutableLiveData<Boolean>()
    val isLogoutSuccess: LiveData<Boolean> get() = _isLogoutSuccess

    fun logout() {
        viewModelScope.launch {
            runCatching {
                authService.logout(LogoutRequest(dearTodaySharedPreferences.deviceToken))
            }.onSuccess {
                dearTodaySharedPreferences.dearTodayToken = ""
                _isLogoutSuccess.value = true
            }.onFailure {
                _isLogoutSuccess.value = false
            }
        }
    }
}