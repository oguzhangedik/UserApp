package com.example.userapp.core.di.modules

import android.content.Context
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.local.LocalDataImpl
import com.example.userapp.core.data.remote.AppService
import com.example.userapp.core.data.repository.RemoteDataRepository
import com.example.userapp.core.data.repository.RemoteDataRepositoryImpl
import com.example.userapp.core.data.room.dao.UserDao
import com.example.userapp.core.netwok.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideNetwork(@ApplicationContext context: Context): Network {
        return Network(context)
    }
    @Singleton
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
    @Singleton
    @Provides
    fun provideLocalRepository(@ApplicationContext context: Context, userDao: UserDao): LocalData {
        return LocalDataImpl(context, userDao)
    }
    @Singleton
    @Provides
    fun provideRemoteRepository(service: AppService, network: Network): RemoteDataRepository {
        return RemoteDataRepositoryImpl(service, network)
    }
}
