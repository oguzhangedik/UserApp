package com.example.userapp.core.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.room.BaseDao

@Dao
abstract class UserDao : BaseDao<LoginResponse>() {
    @Query("SELECT * FROM LoginResponse")
    abstract fun getAll(): List<LoginResponse>
}
