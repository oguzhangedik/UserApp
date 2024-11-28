package com.example.userapp.core.netwok

import com.example.userapp.core.netwok.token.BaseTokenManager
import com.example.userapp.core.utils.Constants.TOKEN_HEADER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MainRestHeadersInterceptor @Inject constructor(
    private val tokenManager: BaseTokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
            .header(TOKEN_HEADER, runBlocking { tokenManager.getAccessToken().first() ?: "" })
            .header("Accept-Charset", "utf-8")
            .build()
        )
    }

}
