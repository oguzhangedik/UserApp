package com.example.userapp.core.common.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.userapp.core.extensions.JsonSerializer.toJson
import com.example.userapp.core.extensions.JsonSerializer.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import javax.inject.Inject

open class DataStoreImpl @Inject constructor(
    open val dataStore: DataStore<Preferences>
): AppDataStore {
    private val tokenMutex = Mutex()

    override suspend fun getLang(): String? {
        return dataStore.data.first()[LANGUAGE]
    }

    override suspend fun setLang(language: String?) {
        dataStore.edit { prefs ->
            language?.let { prefs[LANGUAGE] = it } ?: prefs.remove(LANGUAGE)
        }
    }

    override suspend fun getFcmToken(): String? {
        return dataStore.data.first()[FCM_TOKEN]
    }

    override suspend fun setFcmToken(fcmToken: String?) {
        dataStore.edit { prefs ->
            fcmToken?.let { prefs[FCM_TOKEN] = it } ?: prefs.remove(FCM_TOKEN)
        }
    }

    override suspend fun getLogin(): Boolean? {
        return dataStore.data.first()[IS_LOGIN]
    }

    override suspend fun setLogin(login: Boolean?) {
        dataStore.edit { prefs ->
            login?.let { prefs[IS_LOGIN] = it } ?: prefs.remove(IS_LOGIN)
        }
    }

    override suspend fun getToken(): String? {
        return tokenMutex.withLock {
             dataStore.data.first()[TOKEN]
        }
    }

    override suspend fun setToken(token: String?) {
        tokenMutex.withLock {
            dataStore.edit { prefs ->
                token?.let { prefs[TOKEN] = it } ?: prefs.remove(TOKEN)
            }
        }
    }

    override suspend fun getRefreshToken(): String? {
        return tokenMutex.withLock {
            dataStore.data.first()[REFRESH_TOKEN]
        }
    }

    override suspend fun setRefreshToken(token: String?) {
        tokenMutex.withLock {
            dataStore.edit { prefs ->
                token?.let { prefs[REFRESH_TOKEN] = it } ?: prefs.remove(REFRESH_TOKEN)
            }
        }
    }

    override suspend fun removeToken() {
        tokenMutex.withLock {
            dataStore.edit { prefs ->
                prefs.remove(TOKEN)
            }
        }
    }

    override suspend fun getNotificationId(): String? {
        return dataStore.data.first()[NOTIFICATION_ID]
    }

    override suspend fun setNotificationId(id: String?) {
        dataStore.edit { prefs ->
            id?.let { prefs[NOTIFICATION_ID] = it } ?: prefs.remove(NOTIFICATION_ID)
        }
    }

    override suspend fun getAppVersionName(): String? {
        return dataStore.data.first()[APP_VERSION_NAME]
    }

    override suspend fun setAppVersionName(versionName: String?) {
        dataStore.edit { prefs ->
            versionName?.let { prefs[APP_VERSION_NAME] = it } ?: prefs.remove(APP_VERSION_NAME)
        }
    }


    /**
     * These two methods are going to be used for JSON Encryption usage on Data Store
     */
    inline fun <reified T> getValue(
        key: String, defaultValue: T? = null
    ): Flow<T?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val value = preferences[stringPreferencesKey(key)]
        if (value.isNullOrEmpty()) {
            return@map defaultValue
        }
        value.toObject() ?: defaultValue
    }

    suspend inline fun <reified T> setValue(key: String, newValue: T) {
        newValue?.toJson<T>()?.let { newValueNotNull ->
            dataStore.edit { mutablePreferences ->
                mutablePreferences[stringPreferencesKey(key)] = newValueNotNull
            }
        }
    }

    suspend inline fun removeValue(key: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences.remove(stringPreferencesKey(key))
        }
    }

    companion object {
        private val IS_LOGIN = booleanPreferencesKey("is_login")
        private val TOKEN = stringPreferencesKey("token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val FCM_TOKEN = stringPreferencesKey("fcm_token")
        private val LANGUAGE = stringPreferencesKey("language")
        private val NOTIFICATION_ID = stringPreferencesKey("234")
        private val APP_VERSION_NAME = stringPreferencesKey("app_version_name")
    }
}
