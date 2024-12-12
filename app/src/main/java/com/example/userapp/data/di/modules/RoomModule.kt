package com.example.userapp.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.userapp.data.room.AppDatabase
import com.example.userapp.data.room.dao.GithubUserDao
import com.example.userapp.data.room.dao.GithubUserDetailDao
import com.example.userapp.data.room.dao.GithubUserSearchRequestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "base"
    ).build()

    @Singleton
    @Provides
    fun provideGithubUserSearchRequestDao(db: AppDatabase)
    : GithubUserSearchRequestDao = db.GithubUserSearchRequestDao()

    @Singleton
    @Provides
    fun provideGithubUserDao(db: AppDatabase)
    : GithubUserDao = db.GithubUserDao()

    @Singleton
    @Provides
    fun provideGithubUserDetailDao(db: AppDatabase)
    : GithubUserDetailDao = db.GithubUserDetailDao()
}
