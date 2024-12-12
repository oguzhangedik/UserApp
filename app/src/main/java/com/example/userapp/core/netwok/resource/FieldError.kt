package com.example.userapp.core.netwok.resource

import com.squareup.moshi.Json

data class FieldError(
    @Json(name = "field") val field: String? = null,
    @Json(name = "message") val message: String? = null
)
