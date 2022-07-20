package co.kr.deartoday.data

import co.kr.deartoday.BuildConfig
import co.kr.deartoday.data.service.AuthService
import co.kr.deartoday.data.service.MessageBoxService
import co.kr.deartoday.data.service.TapeService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = BuildConfig.BASE_URL
    var accessToken = ""

    private fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val authRequest =
                if (accessToken == "") {
                    chain.request()
                        .newBuilder()
                        .addHeader("Authorization", accessToken)
                        .build()
                } else {
                    chain.request()
                        .newBuilder()
                        .addHeader("Authorization", accessToken)
                        .build()
                }
            chain.proceed(authRequest)
        }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//    private val client = OkHttpClient.Builder()
//        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        })
//        .build()
//
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    val tapeService: TapeService = provideRetrofit().create(TapeService::class.java)
    val authService: AuthService = provideRetrofit().create(AuthService::class.java)
    val messageBoxService: MessageBoxService =
        provideRetrofit().create(MessageBoxService::class.java)
}