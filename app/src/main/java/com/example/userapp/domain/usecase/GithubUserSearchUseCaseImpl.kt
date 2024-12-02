package com.example.userapp.domain.usecase

import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.data.local.LocalData
import com.example.userapp.domain.repository.GithubUserRepository
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GithubUserSearchUseCaseImpl @Inject constructor(
    private val githubUserRepository: GithubUserRepository,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : GithubUserSearchUseCase {
    override suspend fun searchGithubUsers(request: GithubUserSearchRequest)
    : Flow<Resource<GithubUserSearchDto?>> = flow {
            emit(githubUserRepository.searchGithubUsers(request).first())
        }.flowOn(coroutine)
}
