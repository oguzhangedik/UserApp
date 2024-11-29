package com.example.userapp.core.data.local

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.login.LoginRequest
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest

interface LocalData {
    fun doLogin(loginRequest: LoginRequest): Result<LoginResponse>
    fun doTest(): Result<List<String>>

    suspend fun insertGithubUserSearchRequest(request: GithubUserSearchRequest) : Long

    suspend fun getAllGithubUserSearchRequests(): List<GithubUserSearchRequest>

    suspend fun deleteGithubUserSearchRequest(request: GithubUserSearchRequest)

    suspend fun getByUserSearchRequestParams(request: GithubUserSearchRequest): List<GithubUserSearchRequest>


    suspend fun insertGithubUser(githubUser: GithubUser)

    suspend fun getAllGithubUsers(): List<GithubUser>

    suspend fun deleteGithubUser(githubUser: GithubUser)

    suspend fun getGithubUsersBySearchRequestDbId(searchRequestDbId: Long): List<GithubUser>

    suspend fun insertGithubUsers(githubUsers: List<GithubUser>) : List<Long>


}
