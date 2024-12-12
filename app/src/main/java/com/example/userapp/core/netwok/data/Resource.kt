package com.example.userapp.core.netwok.data

import com.example.userapp.core.netwok.resource.ErrorResponse

data class Resource<out T>(
    val status: Status, val data: T?, val error: ErrorResponse?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> =
            Resource(status = Status.SUCCESS, data = data,error = null)

        fun <T> error(error: ErrorResponse?): Resource<T> =
            Resource(status = Status.ERROR, data = null, error = error)
    }
}
