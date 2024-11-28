package com.example.userapp.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.userapp.core.common.dataStore.AppDataStore
import com.example.userapp.core.common.dataStore.DataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatastoreModule {

    companion object {
        const val APP_CONFIGURATION = "APP_CONFIGURATION"
        private const val DATASTORE_FILE_NAME = "preferences"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_FILE_NAME)

        @Singleton
        @Provides
        fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
            return appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE)
        }

        @Provides
        fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
            return appContext.dataStore
        }
    }

    @Singleton
    @Binds
    fun bindAppDataStore(impl: DataStoreImpl): AppDataStore
}
