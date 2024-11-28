package com.example.userapp.core.netwok.interceptor

import com.example.userapp.core.netwok.annotation.BaseUrl
import com.example.userapp.core.netwok.retrofit.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class HttpRequestInterceptor @Inject constructor(private val networkConfig: NetworkConfig) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())

        val shouldMakeBaseUrl =
            invocation.method().annotations.any { it1 -> it1.annotationClass == BaseUrl::class }

        val originalRequest = chain.request()
        val url = networkConfig.getHostUrl(httpUrl = originalRequest.url, shouldMakeBaseUrl = shouldMakeBaseUrl)
        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
