package com.example.userapp.core.netwok.retrofit

import com.example.userapp.BuildConfig
import com.example.userapp.core.netwok.DEFAULT_CALL_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_CONNECT_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_READ_TIMEOUT_MILLIS
import com.example.userapp.core.netwok.DEFAULT_WRITE_TIMEOUT_MILLIS
import com.example.userapp.core.utils.Constants

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfigImpl @Inject constructor(): NetworkConfig {
    override fun getHostUrl(httpUrl: HttpUrl, shouldMakeBaseUrl: Boolean): HttpUrl {
        val appBaseUrl = Constants.APP_BASE_URL
        return if (appBaseUrl.isNotEmpty() && !shouldMakeBaseUrl) {
            httpUrl.toString().replace(BuildConfig.API_BASE_URL.removeSuffix("/"), appBaseUrl)
                .toHttpUrlOrNull() ?: httpUrl
        } else {
            httpUrl
        }
    }

    override fun getBaseUrl(): String = BuildConfig.API_BASE_URL

    override fun getdefaultCallTimeoutMillis(): Long
        = DEFAULT_CALL_TIMEOUT_MILLIS


    override fun getdefaultConnectTimeoutMillis(): Long
        = DEFAULT_CONNECT_TIMEOUT_MILLIS


    override fun getdefaultReadTimeoutMillis(): Long
        = DEFAULT_READ_TIMEOUT_MILLIS

    override fun getdefaultWriteTimeoutMillis(): Long
        = DEFAULT_WRITE_TIMEOUT_MILLIS
}


