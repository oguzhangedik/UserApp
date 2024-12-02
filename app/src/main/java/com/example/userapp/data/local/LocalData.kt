package com.example.userapp.data.local

import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest

interface LocalData {

    suspend fun insertGithubUserSearchRequest(request: GithubUserSearchRequest) : Long

    suspend fun getAllGithubUserSearchRequests(): List<GithubUserSearchRequest>

    suspend fun deleteGithubUserSearchRequest(request: GithubUserSearchRequest)

    suspend fun getByUserSearchRequestParams(request: GithubUserSearchRequest): List<GithubUserSearchRequest>


    suspend fun insertGithubUser(githubUser: GithubUser)

    suspend fun getAllGithubUsers(): List<GithubUser>

    suspend fun deleteGithubUser(githubUser: GithubUser)

    suspend fun getGithubUsersBySearchRequestDbId(searchRequestDbId: Long): List<GithubUser>

    suspend fun insertGithubUsers(githubUsers: List<GithubUser>) : List<Long>

    suspend fun updateGithubUser(githubUser: GithubUser)


    suspend fun getGithubUserDetailByGithubUserId(githubUser: GithubUser): GithubUserDetail?

    suspend fun insertGithubUserDetail(githubUserDetail: GithubUserDetail) : Long
}
