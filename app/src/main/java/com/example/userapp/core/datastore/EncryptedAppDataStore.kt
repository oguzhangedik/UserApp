package com.example.userapp.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.userapp.core.common.dataStore.DataStoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


class EncryptedAppDataStore @Inject constructor(
    override val dataStore: DataStore<Preferences>
) : DataStoreImpl(dataStore) {

    var schoolHostUrl: Flow<String?>
        get() = getValue(
            key = HOST_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collectLatest {
                    setValue(HOST_KEY, it)
                }
            }
        }

    var schoolName: Flow<String?>
        get() = getValue(
            key = SCHOOL_NAME_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collectLatest {
                    setValue(SCHOOL_NAME_KEY, it)
                }
            }
        }

    companion object {
        /**
         * DATASTORE KEYS
         */
        const val AUTH_OLD_USER_KEY: String = "authOldUser"
        const val HOST_KEY: String = "schoolApiHost"
        const val SCHOOL_NAME_KEY: String = "schoolNameKey"
        const val AUTH_USER_KEY: String = "authUser"
    }
}

