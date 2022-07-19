package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.ServiceCreator
import co.kr.deartoday.data.model.request.auth.AuthRequest
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import kotlinx.coroutines.launch
import timber.log.Timber

class SignInViewModel : ViewModel() {

    //디어투데이 사용자 토큰
    private var _accessToken = MutableLiveData<String>()
    val accessToken : LiveData<String> get() = _accessToken

    fun login(social: String, socialToken: String) {
        viewModelScope.launch {
            runCatching {
                Timber.v("log.앞 카카오::[${social}] + 소셜토큰::[${socialToken}]")
                ServiceCreator.authService.getAccessToken(
                    social,
                    AuthRequest(socialToken, "1234")
                )
            }.onSuccess {
                //TODO 쉐어드 프리퍼런스에 넣는 코드 or 함수 구현
                _accessToken.value = it.data.accessToken
                Timber.v("[${it.data.accessToken}]")
            }.onFailure {
                Timber.e("$it")
            }
        }
    }
}
