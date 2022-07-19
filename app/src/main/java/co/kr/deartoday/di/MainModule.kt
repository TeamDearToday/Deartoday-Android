package co.kr.deartoday.di

import co.kr.deartoday.data.repository.MainRepositoryImpl
import co.kr.deartoday.data.service.MainService
import co.kr.deartoday.domain.repository.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    @Provides
    @Singleton
    fun provideMainRepository(
        mainService: MainService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): MainRepository = MainRepositoryImpl(mainService, coroutineDispatcher)
}