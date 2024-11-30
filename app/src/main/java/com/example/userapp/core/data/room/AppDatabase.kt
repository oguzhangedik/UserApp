package com.example.userapp.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.room.dao.GithubUserDao
import com.example.userapp.core.data.room.dao.GithubUserDetailDao
import com.example.userapp.core.data.room.dao.GithubUserSearchRequestDao

@Database(entities = [GithubUserSearchRequest::class,
    GithubUser::class,
    GithubUserDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun GithubUserSearchRequestDao(): GithubUserSearchRequestDao
    abstract fun GithubUserDao(): GithubUserDao
    abstract fun GithubUserDetailDao(): GithubUserDetailDao
}
