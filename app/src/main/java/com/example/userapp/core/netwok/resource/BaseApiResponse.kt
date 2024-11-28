package com.example.userapp.core.netwok.resource

import com.squareup.moshi.Json

data class BaseApiResponse<T>(
    @Json(name = "Meta")
    val meta: MetaResponse?,
    @Json(name = "Data")
    val data: T?
)
