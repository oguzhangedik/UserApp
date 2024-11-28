package com.example.userapp.core.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.userapp.core.datastore.EncryptedAppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideEncryptedDataStore(
        dataStore: DataStore<Preferences>
    ): EncryptedAppDataStore =
        EncryptedAppDataStore(dataStore = dataStore)
}
