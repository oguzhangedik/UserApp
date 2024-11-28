package com.example.userapp.core.data.usecase.randomuser

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.user.RandomUsers
import kotlinx.coroutines.flow.Flow

interface RandomUserUseCase {
    suspend fun fetchData(): Flow<Result<RandomUsers>>
}
