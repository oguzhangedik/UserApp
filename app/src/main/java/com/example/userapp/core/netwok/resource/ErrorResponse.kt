package com.example.userapp.core.netwok.resource

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "errorCode") val errorCode: Int? = null,
    @Json(name = "message") val message: String? = null,
    @Json(name = "errors") val errors: List<FieldError>? = null
)
