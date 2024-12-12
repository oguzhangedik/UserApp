package com.example.userapp.domain.usecase

import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserSearchUseCase {
    suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserSearchDto?>>
}
