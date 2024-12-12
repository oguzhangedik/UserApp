package com.example.userapp.core.netwok.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ServiceRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let { request ->
        val url = request.url.newBuilder().build()
        val newRequest = request.newBuilder().url(url)
        newRequest.addHeader("Content-Type","application/json")
        newRequest.addHeader("User-Agent","UserApp")
        chain.proceed(newRequest.build())
    }
}
