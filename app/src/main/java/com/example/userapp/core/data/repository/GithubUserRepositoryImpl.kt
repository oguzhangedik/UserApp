package com.example.userapp.core.data.repository

import androidx.annotation.WorkerThread
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserDetailRequest
import com.example.userapp.core.data.dto.user.GithubUserSearchDto
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
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
    override suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetail?>> {
       return handleResponse(networkConnectivity) {
           appClient.githubUserDetail(request)
       }
    }
}
