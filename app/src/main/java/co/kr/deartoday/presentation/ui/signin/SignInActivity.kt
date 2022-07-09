package co.kr.deartoday.presentation.ui.signin

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.kr.deartoday.R
import co.kr.deartoday.databinding.ActivitySignInBinding
import co.kr.deartoday.presentation.ui.base.BaseActivity
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnLayoutButtonClickListener()
    }

    private fun setOnLayoutButtonClickListener() {
        binding.layoutButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                        Toast.makeText(this, "카카오 관련 에러 발생", Toast.LENGTH_SHORT).show()
                    } else if (token != null) {
                        Timber.tag(ContentValues.TAG).i("로그인 성공 $token.accessToken")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                    if (error != null) {
                        Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                        Toast.makeText(this, "카카오 관련 에러 발생", Toast.LENGTH_SHORT).show()
                    } else if (token != null) {
                        Timber.tag(ContentValues.TAG).i("로그인 성공 ${token.accessToken}")
                    }
                }
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


