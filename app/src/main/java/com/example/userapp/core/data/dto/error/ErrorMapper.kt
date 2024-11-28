package com.example.userapp.core.data.dto.error

import android.content.Context
import com.example.userapp.R
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

object ErrorMapper {

    @ApplicationContext
    lateinit var context: Context

    fun getError(e: Exception): ErrorResponse {
        return when (e) {
            is ConnectException -> ErrorResponse(NO_INTERNET_CONNECTION, "connectivity_error")
            is HttpException -> {
                when (e.code()) {
                    in 499..599 -> {
                        ErrorResponse(e.code(), "something_went_wrong")
                    }
                    else -> {
                        try {
                            ErrorResponse(e.code(), e.response()?.errorBody()?.string())
                        } catch (exception: Exception) {
                            ErrorResponse(e.code(), "something_went_wrong")
                        }
                    }
                }
            }

            is JsonSyntaxException -> ErrorResponse(SYNTAX_EXCEPTION, "something_went_wrong")

            is UnknownHostException -> ErrorResponse(UNKNOWN_ERROR, "something_went_wrong")
            is NullPointerException -> ErrorResponse(PASS_WORD_ERROR, "login_error")
            else -> ErrorResponse(UNKNOWN_ERROR, "something_went_wrong")
        }
    }
}
const val NO_INTERNET_CONNECTION = -1
const val NETWORK_ERROR = -2
const val DEFAULT_ERROR = -3
const val PASS_WORD_ERROR = -101
const val USER_NAME_ERROR = -102
const val CHECK_YOUR_FIELDS = -103
const val SEARCH_ERROR = -104
const val SYNTAX_EXCEPTION = -105
const val UNKNOWN_ERROR = -106
