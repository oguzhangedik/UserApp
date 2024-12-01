package com.example.userapp.core.di.modules

import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.GithubUserRepository
import com.example.userapp.core.data.usecase.GithubUserDetailUseCase
import com.example.userapp.core.data.usecase.GithubUserDetailUseCaseImpl
import com.example.userapp.core.data.usecase.GithubUserUseCase
import com.example.userapp.core.data.usecase.GithubUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object GithubUserUseCaseModule {
    @Provides
    fun provideGithubUserUseCase(
        githubUserRepository: GithubUserRepository,
        localRepository: LocalData,
        coroutine: CoroutineContext
    ): GithubUserUseCase {
        return GithubUserUseCaseImpl(githubUserRepository, localRepository, coroutine)
    }

    @Provides
    fun provideGithubUserDetailUseCase(
        githubUserRepository: GithubUserRepository,
        localRepository: LocalData,
        coroutine: CoroutineContext
    ): GithubUserDetailUseCase {
        return GithubUserDetailUseCaseImpl(githubUserRepository, localRepository, coroutine)
    }
}