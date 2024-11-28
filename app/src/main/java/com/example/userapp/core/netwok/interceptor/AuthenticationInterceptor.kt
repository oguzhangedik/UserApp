package com.example.userapp.core.netwok.interceptor

import com.example.userapp.core.common.dataStore.AppDataStore
import com.example.userapp.core.netwok.annotation.Authenticated
import com.example.userapp.core.netwok.annotation.AuthenticatedWithoutBearer
import com.example.userapp.core.netwok.token.BaseTokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import timber.log.Timber
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val tokenManager: BaseTokenManager,
    private val appDataStore: AppDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let { request ->
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader =
            invocation.method().annotations.any { it1 -> it1.annotationClass == Authenticated::class }

        val shouldAttachAuthHeaderWithoutBearer =
            invocation.method().annotations.any { it1 -> it1.annotationClass == AuthenticatedWithoutBearer::class }

        /**
         * Adding Queries to Url
         */
        val url = request.url.newBuilder()
            .build()

        val newRequest = request.newBuilder()
            .url(url)

        newRequest.addHeader("Content-Type","application/json")
        newRequest.addHeader("cmsWebsiteId","1")
        newRequest.addHeader("cmsLanguageId","1")
        newRequest.addHeader("catalogCurrencyId","1")
        newRequest.addHeader("vvMobilePlatform","Android")
        runBlocking {
            newRequest.addHeader("vvMobileVersion", appDataStore.getAppVersionName() ?: "" )
        }

        /**
         * Adding token to [Authenticated] annotated functions
         */
        if (shouldAttachAuthHeader) {
            runBlocking {
                newRequest.addHeader("Authorization", "Bearer ${tokenManager.getAccessToken().first()}")
                Timber.d("New Token With Bearer")
            }
        }

        if (shouldAttachAuthHeaderWithoutBearer) {
            runBlocking {
                newRequest.addHeader("Authorization", "${tokenManager.getAccessToken().first()}")
                Timber.d("New Token WithoutBearer is received")
            }
        }

        chain.proceed(newRequest.build())
    }
}
