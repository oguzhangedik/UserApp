package com.example.userapp.core.di.modules

import com.example.userapp.core.common.preferences.PreferenceManager
import com.example.userapp.core.common.preferences.PreferenceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferenceManagerBinderModule {

    @Singleton
    @Binds
    fun bindPreferenceManager(preferenceManagerImpl: PreferenceManagerImpl): PreferenceManager
}
