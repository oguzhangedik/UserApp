package com.example.userapp.core.data.remote

import com.example.userapp.core.data.dto.user.GithubUserResponse
import com.example.userapp.core.netwok.resource.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AppService {
    object Api {
        const val searchGithubUsers = "search/users"
    }
    @GET(Api.searchGithubUsers)
    suspend fun searchGithubUsers(
        @QueryMap queryParams: Map<String, String>
    ): Response<BaseApiResponse<GithubUserResponse?>>
}
