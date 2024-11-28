package com.example.userapp.core.netwok.resource

import com.squareup.moshi.Json

data class MetaResponse(
    @Json(name = "Success")
    val success: Boolean?,
    /*@Json(name = "Pager")
    val pager: Any?,*/
    @Json(name = "Messages")
    val messages: List<MetaMessage>?,
    @Json(name = "ErrorStatusCode")
    val errorStatusCode: Int?
)

data class MetaMessage(
    @Json(name = "Key")
    val key: String?,
    @Json(name = "Value")
    val value: List<String>?,
)
