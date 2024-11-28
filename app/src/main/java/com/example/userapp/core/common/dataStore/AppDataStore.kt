package com.example.userapp.core.common.dataStore

interface AppDataStore {

    suspend fun getLang(): String?

    suspend fun setLang(language: String?)

    suspend fun getFcmToken(): String?

    suspend fun setFcmToken(fcmToken: String?)

    suspend fun getLogin(): Boolean?

    suspend fun setLogin(login: Boolean?)

    suspend fun getToken(): String?

    suspend fun setToken(token: String?)

    suspend fun getRefreshToken(): String?

    suspend fun setRefreshToken(token: String?)

    suspend fun removeToken()

    suspend fun getNotificationId(): String?

    suspend fun setNotificationId(id: String?)

    suspend fun getAppVersionName() : String?

    suspend fun setAppVersionName(versionName : String?)
}
