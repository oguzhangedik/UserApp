package com.example.userapp.core.data.repository

import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserDetailRequest
import com.example.userapp.core.data.dto.user.GithubUserSearchDto
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserSearchDto?>>
    suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetail?>>
}
