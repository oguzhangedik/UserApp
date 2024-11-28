package com.example.userapp.core.data.repository

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.user.RandomUsers

interface RemoteDataRepository {
    suspend fun fetchData(): Result<RandomUsers>
}
