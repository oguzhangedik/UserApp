package com.example.userapp.core.netwok.retrofit

import okhttp3.HttpUrl

interface NetworkConfig {
    fun getHostUrl(httpUrl: HttpUrl, shouldMakeBaseUrl: Boolean): HttpUrl
    fun getBaseUrl(): String
    fun getdefaultCallTimeoutMillis(): Long
    fun getdefaultConnectTimeoutMillis(): Long
    fun getdefaultReadTimeoutMillis(): Long
    fun getdefaultWriteTimeoutMillis(): Long
}
