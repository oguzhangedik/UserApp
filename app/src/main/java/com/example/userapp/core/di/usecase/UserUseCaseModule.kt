package com.example.userapp.core.di.usecase

import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.RemoteDataRepository
import com.example.userapp.core.data.usecase.user.UserUseCase
import com.example.userapp.core.data.usecase.user.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {
    @Provides
    fun provideUseCaseLogin(
        remoteRepository: RemoteDataRepository,
        localRepository: LocalData,
        coroutine: CoroutineContext
    ): UserUseCase {
        return UserUseCaseImpl(remoteRepository, localRepository, coroutine)
    }
}
