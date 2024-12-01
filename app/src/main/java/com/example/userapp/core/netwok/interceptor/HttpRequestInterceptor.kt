package com.example.userapp.core.netwok.interceptor

import com.example.userapp.core.netwok.retrofit.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpRequestInterceptor @Inject constructor(private val networkConfig: NetworkConfig) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val url = networkConfig.getHostUrl(httpUrl = originalRequest.url)
        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
