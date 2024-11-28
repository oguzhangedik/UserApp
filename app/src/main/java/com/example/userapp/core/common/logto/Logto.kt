package com.example.userapp.core.common.logto

import android.app.Activity
import android.app.Application
import com.example.userapp.core.common.logto.data.AccessTokenApp
import com.example.userapp.core.common.logto.data.LogToConfigApp
import com.example.userapp.core.common.logto.data.Prompt
import io.logto.sdk.android.LogtoClient
import io.logto.sdk.android.exception.LogtoException
import io.logto.sdk.android.type.LogtoConfig
import io.logto.sdk.core.constant.PromptValue
import timber.log.Timber

/**
 * you should add to global application
 * you should firstly call getConfigs method and then you call getClient
 *
 * @see logto.io */
object LogTo {

    private lateinit var logToConfig: LogtoConfig
    lateinit var logToClient: LogtoClient

    fun getConfig(logToConfigApp: LogToConfigApp): LogtoConfig {
        logToConfig = LogtoConfig(
            endpoint = logToConfigApp.endpoint,
            appId = logToConfigApp.appId,
            scopes = logToConfigApp.scopes,
            resources = logToConfigApp.resources,
            usingPersistStorage = logToConfigApp.usingPersistStorage,
            prompt = when(logToConfigApp.prompt) {
                Prompt.Consent -> PromptValue.CONSENT
                Prompt.Login -> PromptValue.LOGIN
            }
        )
        return logToConfig
    }

    fun getClient(application: Application): LogtoClient {
        logToClient = LogtoClient(logToConfig, application)
        return logToClient
    }

    /**
     * sigIn method uses only login screen * @return string is give me token
     * */

    fun signIn(activity: Activity, redirectUri: String, myFunc: (AccessTokenApp?) -> Unit) {
        logToClient.signIn(activity, redirectUri) {
            it?.let { logtoException: LogtoException ->
                Timber.tag("signInError").e(logtoException.toString())
            } ?: getAccessTokenLogin(myFunc)
        }
    }

    /**
     * getAccessToken is for services call
     * @return string is give me token
     * */

    fun getAccessToken(baseUrl: String, myFunc: (String?) -> Unit) {
        logToClient.getAccessToken(baseUrl) { logtoException, accessToken ->
            Timber.tag("signInError").e(logtoException, "getAccessToken: ")
            myFunc(accessToken?.token.toString())
        }
    }

    private fun getAccessTokenLogin(myFunc: (AccessTokenApp?) -> Unit) {
        logToClient.getAccessToken { logtoException, accessToken ->
            logtoException?.let { }
                ?: accessToken?.let {
                    myFunc(AccessTokenApp(token = accessToken.token, scope = accessToken.scope, expiresAt = accessToken.expiresAt))
                }
        }
    }

    fun signOut(myFunc: () -> Unit) {
        logToClient.signOut { logtoException: LogtoException? ->
            myFunc()
        }
    }
}
