package com.example.userapp.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.room.dao.UserDao

@Database(entities = [LoginResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}
