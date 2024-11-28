package com.example.userapp.core.data.usecase.randomuser

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.user.RandomUsers
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RandomUserUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteDataRepository,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) :
    RandomUserUseCase {
    override suspend fun fetchData(): Flow<Result<RandomUsers>> {
        return flow {
            emit(remoteRepository.fetchData())
        }.flowOn(coroutine)
    }
}
