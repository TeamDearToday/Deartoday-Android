package co.kr.deartoday.di

import co.kr.deartoday.data.service.auth.AuthService
import co.kr.deartoday.data.service.messagebox.MessageBoxService
import co.kr.deartoday.data.service.tape.TapeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideTapeService(retrofit: Retrofit): TapeService =
        retrofit.create(TapeService::class.java)

    @Provides
    @Singleton
    fun provideMessageBoxService(retrofit: Retrofit): MessageBoxService =
        retrofit.create(MessageBoxService::class.java)
}