package co.kr.deartoday.presentation.viewmodel.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.ServiceCreator
import co.kr.deartoday.data.model.request.auth.LoginRequest
import co.kr.deartoday.data.service.auth.AuthService
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authService: AuthService,
    private val sharedPreferences: DearTodaySharedPreferences
) : ViewModel() {

    //디어투데이 사용자 토큰
    private var _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

    fun login(social: String, socialToken: String) {
        viewModelScope.launch {
            runCatching {
                Timber.v("log.앞 카카오::[${social}] + 소셜토큰::[${socialToken}]")
                authService.login(
                    social,
                    LoginRequest(socialToken, sharedPreferences.deviceToken)
                )
            }.onSuccess {
                _accessToken.value = it.data.accessToken
                ServiceCreator.accessToken = it.data.accessToken
                Timber.d("최우형[${it.data.accessToken}]", "[${it.data.accessToken}]")
            }.onFailure {
                Timber.e("$it")
            }
        }
    }
}
