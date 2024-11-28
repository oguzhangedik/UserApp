package com.example.userapp.core.netwok.token

import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.util.Date

abstract class BaseTokenManager {

    abstract suspend fun refreshAccessToken(): Boolean

    /**
     * Since an api request will be established,
     * it is better to return token as a flow
     */
    abstract suspend fun getAccessToken(): Flow<String?>

    abstract suspend fun signOut()

    /**
     * If JWT Token is used, this method could be used.
     */
    open fun checkIfJWTExpired(token: String): Boolean {
        var jwtExpiresAt: Date? = null
        val now = System.currentTimeMillis()
        var isExpired = true

        runCatching {
            val jwt = JWT(token)
            jwtExpiresAt = jwt.expiresAt
        }.onSuccess {
            jwtExpiresAt?.let { expireAt ->
                val timeInterval = ((expireAt.time - now) / 1000).toInt()
                if (timeInterval > 0) {
                    isExpired = false
                }
            } ?: run {
                isExpired = true
            }
        }.onFailure {
            Timber.e(it)
            isExpired = true
        }

        return isExpired
    }
}
