package com.example.userapp.core.common.preferences

import android.content.SharedPreferences
import com.example.userapp.core.di.modules.DatastoreModule.Companion.APP_CONFIGURATION
import javax.inject.Inject

class PreferenceManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): PreferenceManager {

    override fun getLang(): String? {
        return sharedPreferences.getString(LANGUAGE, "")
    }

    override fun setLang(language: String?) {
        setString(LANGUAGE, language)
    }

    override fun getFcmToken(): String? {
        return sharedPreferences.getString(FCM_TOKEN, "")
    }

    override fun setFcmToken(fcmToken: String?) {
        setString(FCM_TOKEN, fcmToken)
    }

    override fun getLogin(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

    override fun setLogin(login: Boolean?) {
        setBoolean(IS_LOGIN, login)
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    override fun setToken(token: String?) {
        setString(TOKEN, token)
    }

    override fun getNotificationId(): String? {
        return sharedPreferences.getString(NOTIFICATION_ID,"")
    }

    override fun setNotificationId(id: String?) {
        setString(NOTIFICATION_ID, id)
    }

    override fun clearAll() {
        with(sharedPreferences.edit()) {
            remove(APP_CONFIGURATION)
        }.apply()
    }

    private fun setString(key: String, value: String?) {
        value?.let { sharedPreferences.edit().putString(key, it).apply() }
            ?: sharedPreferences.edit().remove(key).apply()
    }

    private fun setBoolean(key: String, value: Boolean?) {
        value?.let { sharedPreferences.edit().putBoolean(key, it).apply() }
            ?: sharedPreferences.edit().remove(key).apply()
    }

    companion object {
        private const val IS_LOGIN = "is_login"
        private const val TOKEN = "token"
        private const val FCM_TOKEN = "fcm_token"
        private const val LANGUAGE = "language"
        private const val NOTIFICATION_ID = "234"
    }
}
