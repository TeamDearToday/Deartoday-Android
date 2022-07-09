package co.kr.deartoday.application

import android.app.Application
import android.app.UiModeManager.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate
import co.kr.deartoday.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DeartodayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}