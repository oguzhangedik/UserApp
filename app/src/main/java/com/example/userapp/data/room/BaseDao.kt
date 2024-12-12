package com.example.userapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
abstract class BaseDao<T> {

    @Insert
    abstract suspend fun insert(obj: T) : Long
    @Insert
    abstract suspend fun insert(vararg obj: T)

    @Update
    abstract suspend fun update(obj: T)

    @Delete
    abstract suspend fun delete(obj: T)
}
