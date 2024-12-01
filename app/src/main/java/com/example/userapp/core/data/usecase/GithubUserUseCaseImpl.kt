package com.example.userapp.core.data.usecase

import com.example.userapp.core.data.dto.user.GithubUserResponse
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.GithubUserRepository
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GithubUserUseCaseImpl @Inject constructor(
    private val githubUserRepository: GithubUserRepository,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : GithubUserUseCase {
    override suspend fun searchGithubUsers(request: GithubUserSearchRequest)
    : Flow<Resource<GithubUserResponse?>> = flow {
            emit(githubUserRepository.searchGithubUsers(request).first())
        }.flowOn(coroutine)
}
