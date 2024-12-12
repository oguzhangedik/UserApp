package com.example.userapp.data.di.modules

import com.example.userapp.data.local.LocalData
import com.example.userapp.domain.repository.GithubUserRepository
import com.example.userapp.domain.usecase.GithubUserDetailUseCase
import com.example.userapp.domain.usecase.GithubUserDetailUseCaseImpl
import com.example.userapp.domain.usecase.GithubUserSearchUseCase
import com.example.userapp.domain.usecase.GithubUserSearchUseCaseImpl
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
    ): GithubUserSearchUseCase {
        return GithubUserSearchUseCaseImpl(githubUserRepository, localRepository, coroutine)
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