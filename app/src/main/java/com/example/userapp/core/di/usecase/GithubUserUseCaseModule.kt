package com.example.userapp.core.di.usecase

import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.GithubUserRepository
import com.example.userapp.core.data.usecase.githubuser.GithubUserUseCase
import com.example.userapp.core.data.usecase.githubuser.GithubUserUseCaseImpl
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
}
