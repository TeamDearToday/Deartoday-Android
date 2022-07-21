package co.kr.deartoday.presentation.ui.signin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import co.kr.deartoday.databinding.ActivitySignInBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.main.MainActivity
import co.kr.deartoday.presentation.viewmodel.SignInViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import co.kr.deartoday.util.shortToast
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    private val viewModel by viewModels<SignInViewModel>()
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    @Inject
    lateinit var sharedPreferences: DearTodaySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeAccessToken()
        setOnLayoutButtonClickListener()
        getRegistrationToken()
    }

    private fun getRegistrationToken() {
        if(sharedPreferences.deviceToken == "") {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.tag("Firebase Device Token")
                        .w("Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                Timber.tag("Firebase Device Token").d(token)
                sharedPreferences.deviceToken = token
            })
        }
    }

    private var backPressedTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        shortToast("뒤로가기 버튼을 한 번 더 누르면 종료됩니다")
        backPressedTime = System.currentTimeMillis()
    }

    private fun setOnLayoutButtonClickListener() {
        binding.layoutButton.setOnClickListener {
            getAccessToken()
        }
    }

    private fun getAccessToken() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                } else if (token != null) {
                    Timber.tag(ContentValues.TAG).i("로그인 성공 [${token.accessToken}]")
                    viewModel.login("KAKAO", token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                } else if (token != null) {
                    Timber.tag(ContentValues.TAG).i("로그인 성공 ${token.accessToken}")
                    viewModel.login("KAKAO", token.accessToken)
                }
            }
        }
    }

    private fun observeAccessToken() {
        viewModel.accessToken.observe(this) {
            DearTodaySharedPreferences(this).dearTodayToken = it
            startActivity(Intent(this, MainActivity::class.java))
            if (!isFinishing) {
                finish()
            }
        }
    }

    private fun setCaptionVisible() {
        if (true) {
            binding.tvCaption01.visibility = View.VISIBLE
            binding.tvCaption02.visibility = View.VISIBLE
        } else if (false) {
            binding.tvCaption01.visibility = View.GONE
            binding.tvCaption02.visibility = View.GONE
        }
    }
}


