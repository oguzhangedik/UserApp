package com.example.userapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.data.room.dao.GithubUserDao
import com.example.userapp.data.room.dao.GithubUserDetailDao
import com.example.userapp.data.room.dao.GithubUserSearchRequestDao

@Database(entities = [GithubUserSearchRequest::class,
    GithubUser::class,
    GithubUserDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun GithubUserSearchRequestDao(): GithubUserSearchRequestDao
    abstract fun GithubUserDao(): GithubUserDao
    abstract fun GithubUserDetailDao(): GithubUserDetailDao
}
