package com.example.userapp.core.data.repository

import androidx.annotation.WorkerThread
import com.example.userapp.core.data.dto.error.ErrorMapper
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserDetailRequest
import com.example.userapp.core.data.dto.user.GithubUserResponse
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.netwok.Network
import com.example.userapp.core.netwok.client.AppClient
import com.example.userapp.core.netwok.data.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.net.ConnectException
import javax.inject.Inject
import com.example.userapp.core.netwok.NetworkHandler.handleResponse


class GithubUserRepositoryImpl @Inject constructor(
    private val appClient: AppClient,
    private val networkConnectivity: Network
) : GithubUserRepository {

    @WorkerThread
    override suspend fun searchGithubUsers(request: GithubUserSearchRequest): Flow<Resource<GithubUserResponse?>> {

        return handleResponse {
            appClient.searchGithubUsers(request)
        }

        /*return when (val response = processCall(appClient.searchGithubUsers(request))) {
            is GithubUserResponse -> Result.Success(data = response)

            is ErrorResponse -> Result.Error(response)

            else -> Result.Error(null)
        }*/
    }

    @WorkerThread
    override suspend fun githubUserDetail(request: GithubUserDetailRequest): Flow<Resource<GithubUserDetail?>> {
       return handleResponse {
           appClient.githubUserDetail(request)
       }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return ErrorMapper.getError(ConnectException())
        }
        return try {
            val response = responseCall.invoke()
            response.body()
        } catch (e: Exception) {
            ErrorMapper.getError(e)
        }
    }
}
