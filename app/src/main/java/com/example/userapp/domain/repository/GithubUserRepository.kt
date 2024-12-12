package com.example.userapp.domain.repository

import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserSearchDto?>>
    suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetailDto?>>
}
