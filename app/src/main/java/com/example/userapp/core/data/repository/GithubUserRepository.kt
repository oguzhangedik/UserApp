package com.example.userapp.core.data.repository

import com.example.userapp.core.data.dto.user.GithubUserResponse
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserResponse?>>
}
