package com.example.userapp.core.di.modules

import android.content.Context
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.local.LocalDataImpl
import com.example.userapp.core.data.remote.AppService
import com.example.userapp.core.data.repository.GithubUserRepository
import com.example.userapp.core.data.repository.GithubUserRepositoryImpl
import com.example.userapp.core.data.room.dao.UserDao
import com.example.userapp.core.di.qualifers.RetrofitAppService
import com.example.userapp.core.netwok.Network
import com.example.userapp.core.netwok.client.AppClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
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
    fun provideGithubUserRepository(appClient: AppClient, network: Network): GithubUserRepository {
        return GithubUserRepositoryImpl(appClient, network)
    }
}
