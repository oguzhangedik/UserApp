package com.example.userapp.core.netwok.retrofit

import okhttp3.HttpUrl

interface NetworkConfig {
    fun getHostUrl(httpUrl: HttpUrl): HttpUrl
    fun getBaseUrl(): String
    fun getDefaultCallTimeoutMillis(): Long
    fun getDefaultConnectTimeoutMillis(): Long
    fun getDefaultReadTimeoutMillis(): Long
    fun getDefaultWriteTimeoutMillis(): Long
}
