package com.example.userapp.core.netwok.client

import com.example.userapp.core.data.dto.user.GithubUserResponse
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.remote.AppService


import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class AppClient @Inject constructor(
    private val appService: AppService
) {

    suspend fun searchGithubUsers(
        request: GithubUserSearchRequest
    ): Response<GithubUserResponse?> {
        val queryParams = mapOf(
            "q" to request.textInUserNameToSearch,
            "page" to request.page.toString(),
            "per_page" to request.perPageUserCount.toString()
        )
       return appService.searchGithubUsers(queryParams)
    }


}
