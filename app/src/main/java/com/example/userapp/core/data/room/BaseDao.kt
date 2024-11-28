package com.example.userapp.core.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
abstract class BaseDao<T> {

    @Insert
    abstract fun insert(obj: T)
    @Insert
    abstract fun insert(vararg obj: T)

    @Update
    abstract fun update(obj: T)

    @Delete
    abstract fun delete(obj: T)
}
