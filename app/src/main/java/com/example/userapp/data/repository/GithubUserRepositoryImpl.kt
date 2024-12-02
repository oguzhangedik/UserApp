package com.example.userapp.data.repository

import androidx.annotation.WorkerThread
import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.domain.repository.GithubUserRepository
import com.example.userapp.core.netwok.Network
import com.example.userapp.core.netwok.client.AppClient
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.userapp.core.netwok.NetworkHandler.handleResponse


class GithubUserRepositoryImpl @Inject constructor(
    private val appClient: AppClient,
    private val networkConnectivity: Network
) : GithubUserRepository {

    @WorkerThread
    override suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserSearchDto?>> {
        return handleResponse(networkConnectivity) {
            appClient.searchGithubUsers(request)
        }
    }

    @WorkerThread
    override suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetailDto?>> {
       return handleResponse(networkConnectivity) {
           appClient.githubUserDetail(request)
       }
    }
}
