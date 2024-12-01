package com.example.userapp.core.di.modules

import com.example.userapp.ui.userdetail.domain.UserDetailMapper
import com.example.userapp.ui.userdetail.domain.UserDetailMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    fun provideUserDetailMapper(): UserDetailMapper {
        return UserDetailMapperImpl()
    }
}