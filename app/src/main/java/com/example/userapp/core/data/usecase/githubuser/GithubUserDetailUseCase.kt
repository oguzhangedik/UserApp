package com.example.userapp.core.data.usecase.githubuser

import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserDetailRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserDetailUseCase {
    suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetail?>>
}
