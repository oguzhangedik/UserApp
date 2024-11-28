package com.example.userapp.core.data.usecase.user

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.login.LoginRequest
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserUseCaseImpl @Inject constructor(private val remoteRepository: RemoteDataRepository, private val localRepository: LocalData, private val coroutine: CoroutineContext) :
    UserUseCase {
    override suspend fun login(login: LoginRequest): Flow<Result<LoginResponse>> {
        return flow {
            emit(localRepository.doLogin(login))
        }.flowOn(coroutine)
    }

    override suspend fun getListForTest(): Flow<Result<List<String>>> {
        return flow {
            emit(localRepository.doTest())
        }.flowOn(coroutine)
    }
}
