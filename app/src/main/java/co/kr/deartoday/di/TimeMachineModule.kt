package co.kr.deartoday.di

import co.kr.deartoday.data.repository.timemachine.TimeMachineRepositoryImpl
import co.kr.deartoday.data.service.timemachine.TimeMachineService
import co.kr.deartoday.domain.repository.timemachine.TimeMachineRepository
import co.kr.deartoday.presentation.usecase.TimeMachineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeMachineModule {
    @Provides
    @Singleton
    fun provideTimeMachineService(retrofit: Retrofit): TimeMachineService =
        retrofit.create(TimeMachineService::class.java)

    @Provides
    @Singleton
    fun provideTimeMachineRepository(
        timeMachineService: TimeMachineService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): TimeMachineRepository = TimeMachineRepositoryImpl(timeMachineService, coroutineDispatcher)

    @Provides
    @Singleton
    fun provideTimeMachineUseCase(
        timeMachineService: TimeMachineService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): TimeMachineUseCase = TimeMachineUseCase(timeMachineService, coroutineDispatcher)
}