package com.example.userapp.core.netwok.data

import com.example.userapp.core.netwok.resource.ErrorResponse
import com.example.userapp.core.netwok.resource.MetaResponse

data class Resource<out T>(
    val status: Status, val data: T?, val meta : MetaResponse?, val error: ErrorResponse?
) {
    companion object {
        fun <T> success(data: T?, meta : MetaResponse?): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, meta = meta, error = null)

        fun <T> error(error: ErrorResponse?, meta : MetaResponse?): Resource<T> =
            Resource(status = Status.ERROR, data = null, meta = meta, error = error)
    }
}
