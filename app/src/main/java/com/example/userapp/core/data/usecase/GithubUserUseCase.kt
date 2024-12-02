package com.example.userapp.core.data.usecase

import com.example.userapp.core.data.dto.user.GithubUserSearchDto
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserUseCase {
    suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserSearchDto?>>
}
