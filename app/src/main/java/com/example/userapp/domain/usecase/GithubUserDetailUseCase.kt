package com.example.userapp.domain.usecase

import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserDetailUseCase {
    suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetailDto?>>
}
