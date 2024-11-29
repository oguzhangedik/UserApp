package com.example.userapp.core.data.remote

import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AppService {
    object Api {
        const val searchGithubUsers = "search/users"
        const val githubUserDetail = "users/{login}"
    }
    @GET(Api.searchGithubUsers)
    suspend fun searchGithubUsers(
        @QueryMap queryParams: Map<String, String>
    ): Response<GithubUserResponse?>

    @GET(Api.githubUserDetail)
    suspend fun githubUserDetail(
        @Path("login") login: String
    ): Response<GithubUserDetail?>
}
