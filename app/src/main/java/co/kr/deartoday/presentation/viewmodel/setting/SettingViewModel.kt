package co.kr.deartoday.presentation.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    //디어투데이 사용자 토큰
    private var _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

}