package com.example.userapp.data.remote

import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AppService {
    object Api {
        const val searchGithubUsers = "search/users"
        const val githubUserDetail = "users/{login}"
    }
    @GET(Api.searchGithubUsers)
    suspend fun searchGithubUsers(
        @QueryMap queryParams: Map<String, String>
    ): Response<GithubUserSearchDto?>

    @GET(Api.githubUserDetail)
    suspend fun githubUserDetail(
        @Path("login") login: String
    ): Response<GithubUserDetailDto?>
}
