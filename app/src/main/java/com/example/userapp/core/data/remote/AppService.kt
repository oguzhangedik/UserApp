package com.example.userapp.core.data.remote

import com.example.userapp.core.data.dto.user.RandomUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    object Api {
        const val apiVersion = "api/"
    }
    @GET(Api.apiVersion)
    suspend fun fetchUsers(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 10,
    ): Response<RandomUsers>
}
