package co.kr.deartoday.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.deartoday.data.ServiceCreator
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    //request
    private var _social = MutableLiveData<String>()
    val social : LiveData<String> get() = _social

    private var _fcmToken = MutableLiveData<String>()
    val fcmToken : LiveData<String> get() = _fcmToken

    //카카오 토큰
    private var _socialToken = MutableLiveData<String>()
    val socialToken : LiveData<String> get() = _socialToken

    //사용자 토큰
    private var _accessToken = MutableLiveData<String>()
    val accessToken : LiveData<String> get() = _accessToken

    fun setSocialType(type: String){
        _social.value = type
    }

    fun setSocialToken(token: String){
        _socialToken.value = token
    }

    fun setAccessToken(token : String){
        _accessToken.value = token
    }

    fun login(){
        viewModelScope.launch {
            runCatching {

            }.onSuccess {

            }.onFailure {

            }
        }
    }

}
