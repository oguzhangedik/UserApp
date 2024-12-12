package com.example.userapp.domain.usecase

import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.data.local.LocalData
import com.example.userapp.domain.repository.GithubUserRepository
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GithubUserDetailUseCaseImpl @Inject constructor(
    private val githubUserRepository: GithubUserRepository,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : GithubUserDetailUseCase {
    override suspend fun githubUserDetail(request: GithubUserDetailRequest)
    : Flow<Resource<GithubUserDetailDto?>> = flow {
            emit(githubUserRepository.githubUserDetail(request).first())
        }.flowOn(coroutine)
}
