package com.example.userapp.core.common.preferences

interface PreferenceManager {

    fun getLang(): String?

    fun setLang(language: String?)

    fun getFcmToken(): String?

    fun setFcmToken(fcmToken: String?)

    fun getLogin(): Boolean?

    fun setLogin(login: Boolean?)

    fun getToken(): String?

    fun setToken(token: String?)

    fun getNotificationId(): String?

    fun setNotificationId(id: String?)

    fun clearAll()
}
