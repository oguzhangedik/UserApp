package com.example.userapp.data.di.modules

import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailMapper
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailMapperImpl
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