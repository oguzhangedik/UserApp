package com.example.userapp.data.dto

import com.example.userapp.data.dto.error.ErrorResponse

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: ErrorResponse?) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
val Result<*>.succeeded
    get() = this is Result.Success && data != null

val Result<*>.unAuthorized
    get() = (this is Result.Error) &&
        (exception?.error == 401)
