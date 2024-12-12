package com.example.userapp.core.netwok.retrofit

import com.example.userapp.BuildConfig
import com.example.userapp.core.netwok.DEFAULT_CALL_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_CONNECT_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_READ_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_WRITE_TIMEOUT_MILLIS

import okhttp3.HttpUrl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfigImpl @Inject constructor(): NetworkConfig {
    override fun getHostUrl(httpUrl: HttpUrl) = httpUrl

    override fun getBaseUrl(): String = BuildConfig.API_BASE_URL

    override fun getDefaultCallTimeoutMillis(): Long
        = DEFAULT_CALL_TIMEOUT_MILLIS


    override fun getDefaultConnectTimeoutMillis(): Long
        = DEFAULT_CONNECT_TIMEOUT_MILLIS


    override fun getDefaultReadTimeoutMillis(): Long
        = DEFAULT_READ_TIMEOUT_MILLIS

    override fun getDefaultWriteTimeoutMillis(): Long
        = DEFAULT_WRITE_TIMEOUT_MILLIS
}


