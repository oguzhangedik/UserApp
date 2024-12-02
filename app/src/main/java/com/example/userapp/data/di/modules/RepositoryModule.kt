package com.example.userapp.data.di.modules

import android.content.Context
import com.example.userapp.data.local.LocalData
import com.example.userapp.data.local.LocalDataImpl
import com.example.userapp.domain.repository.GithubUserRepository
import com.example.userapp.data.repository.GithubUserRepositoryImpl
import com.example.userapp.data.room.dao.GithubUserDao
import com.example.userapp.data.room.dao.GithubUserDetailDao
import com.example.userapp.data.room.dao.GithubUserSearchRequestDao
import com.example.userapp.core.netwok.Network
import com.example.userapp.core.netwok.client.AppClient
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
    fun provideLocalRepository(@ApplicationContext context: Context,
                               githubUserSearchRequestDao: GithubUserSearchRequestDao,
                               githubUserDao: GithubUserDao,
                               githubUserDetailDao: GithubUserDetailDao
    ): LocalData {
        return LocalDataImpl(context, githubUserSearchRequestDao, githubUserDao, githubUserDetailDao)
    }
    @Singleton
    @Provides
    fun provideGithubUserRepository(appClient: AppClient, network: Network): GithubUserRepository {
        return GithubUserRepositoryImpl(appClient, network)
    }
}
