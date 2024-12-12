package com.example.userapp.core.netwok.client

import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.data.dto.usersearch.GithubUserSearchDto
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.data.remote.AppService


import retrofit2.Response
import javax.inject.Inject

class AppClient @Inject constructor(
    private val appService: AppService
) {

    suspend fun searchGithubUsers(
        request: GithubUserSearchRequest
    ): Response<GithubUserSearchDto?> {
        val queryParams = mapOf(
            "q" to request.textInUserNameToSearch,
            "page" to request.page.toString(),
            "per_page" to request.perPageUserCount.toString()
        )
       return appService.searchGithubUsers(queryParams)
    }

    suspend fun githubUserDetail(
        request: GithubUserDetailRequest
    ): Response<GithubUserDetailDto?> {
        return appService.githubUserDetail(request.login)
    }
}
