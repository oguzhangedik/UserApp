package com.example.userapp.core.data.usecase.user

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.login.LoginRequest
import com.example.userapp.core.data.dto.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun login(login: LoginRequest): Flow<Result<LoginResponse>>
    suspend fun getListForTest(): Flow<Result<List<String>>>
}
