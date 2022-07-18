package co.kr.deartoday.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val FILE_NAME = "DEAR_TODAY_..."

// TODO
// sharedpreferences 를 파일로 만들고 키-값 쌍들을 만들겠죠?
// 아무래도 deartoday token 값을 넣거나 삭제할 수 있어야 하겠죠?
// 추후에 sharedprferences token 값이 있거나 없을 경우 -> 자동 로그인 or 뭐 기타등등 해야겠죠?
// 이 주석은 merge 직전에 사라질 예정입니다.

class DearTodaySharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dearTodaySharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        FILE_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var dearTodayToken: String
        set(value) = dearTodaySharedPreferences.edit { putString("DEAR_TODAY_TOKEN", value) }
        get() = dearTodaySharedPreferences.getString("DEAR_TODAY_TOKEN", "") ?: ""

    var autoLogin: Boolean
        set(value) = dearTodaySharedPreferences.edit { putBoolean("AUTO_LOGIN", value) }
        get() = dearTodaySharedPreferences.getBoolean("AUTO_LOGIN", false)

}