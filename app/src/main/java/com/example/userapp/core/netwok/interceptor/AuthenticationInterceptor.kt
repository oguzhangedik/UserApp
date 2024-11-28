package com.example.userapp.core.netwok.interceptor

import com.example.userapp.core.common.dataStore.AppDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val appDataStore: AppDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let { request ->
        val url = request.url.newBuilder().build()
        val newRequest = request.newBuilder().url(url)
        newRequest.addHeader("Content-Type","application/json")
        newRequest.addHeader("User-Agent","UserApp")
        chain.proceed(newRequest.build())
    }
}
