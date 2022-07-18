package co.kr.deartoday.presentation.ui.signin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivitySignInBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import co.kr.deartoday.presentation.ui.main.MainActivity
import co.kr.deartoday.presentation.viewmodel.SignInViewModel
import co.kr.deartoday.util.shortToast
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    private val viewModel by viewModels<SignInViewModel>()
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, _ ->
        if (token != null) {
            Timber.i("카카오계정 로그인 성공 " + token.accessToken)
            viewModel.setSocialType("KAKAO")
            viewModel.setSocialToken(token.accessToken)
        } else {
            Timber.e(token.toString())
            this.shortToast("다시 로그인해주세요")
        }
        viewModel.login()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnLayoutButtonClickListener()
    }

    //TODO viewmodel 에 작성 요망
    private fun setOnLayoutButtonClickListener() {
        binding.layoutButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                        Toast.makeText(this, "카카오 관련 에러 발생", Toast.LENGTH_SHORT).show()
                    } else if (token != null) {
                        Timber.tag(ContentValues.TAG).i("로그인 성공 $token.accessToken")
                        startActivity(Intent(this, MainActivity::class.java))
                        if (!isFinishing) {
                            finish()
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                    if (error != null) {
                        Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                        Toast.makeText(this, "카카오 관련 에러 발생", Toast.LENGTH_SHORT).show()
                    } else if (token != null) {
                        Timber.tag(ContentValues.TAG).i("로그인 성공 ${token.accessToken}")
                        startActivity(Intent(this, MainActivity::class.java))
                        if (!isFinishing) {
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun getKakaoToken() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            Timber.i("카카오톡으로 로그인 가능")
            UserApiClient.instance.loginWithKakaoTalk(
                this,
                callback = kakaoLoginCallback
            )
        } else {
            Timber.i("카카오톡으로 로그인 가능")
            UserApiClient.instance.loginWithKakaoAccount(
                this,
                callback = kakaoLoginCallback
            )
        }
    }

    private fun login() {

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


