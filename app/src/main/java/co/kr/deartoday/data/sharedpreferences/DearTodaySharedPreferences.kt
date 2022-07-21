package co.kr.deartoday.data.sharedpreferences

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import co.kr.deartoday.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val FILE_NAME = "DEAR_TODAY"

class DearTodaySharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dearTodaySharedPreferences =
        if (BuildConfig.DEBUG) {
            context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        } else {
            EncryptedSharedPreferences.create(
                FILE_NAME,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

    var dearTodayToken: String
        set(value) = dearTodaySharedPreferences.edit { putString("DEAR_TODAY_TOKEN", value) }
        get() = dearTodaySharedPreferences.getString("DEAR_TODAY_TOKEN", "") ?: ""

    var deviceToken: String
        set(value) = dearTodaySharedPreferences.edit { putString("DEVICE_TOKEN", value) }
        get() = dearTodaySharedPreferences.getString("DEVICE_TOKEN", "") ?: ""

    var autoLogin: Boolean
        set(value) = dearTodaySharedPreferences.edit { putBoolean("AUTO_LOGIN", value) }
        get() = dearTodaySharedPreferences.getBoolean("AUTO_LOGIN", false)
}