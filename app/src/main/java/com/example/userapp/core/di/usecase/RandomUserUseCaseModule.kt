package com.example.userapp.core.di.usecase

import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.RemoteDataRepository
import com.example.userapp.core.data.usecase.randomuser.RandomUserUseCase
import com.example.userapp.core.data.usecase.randomuser.RandomUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object RandomUserUseCaseModule {
    @Provides
    fun provideUseCaseMovie(
        remoteRepository: RemoteDataRepository,
        localRepository: LocalData,
        coroutine: CoroutineContext
    ): RandomUserUseCase {
        return RandomUserUseCaseImpl(remoteRepository, localRepository, coroutine)
    }
}
