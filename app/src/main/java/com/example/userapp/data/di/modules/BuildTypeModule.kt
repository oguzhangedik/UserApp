package com.example.userapp.data.di.modules

import com.example.userapp.core.common.build.BuildConfigProvider
import com.example.userapp.core.common.buildConfig.FrameworkBuildConfigProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BuildConfigModule {
    @Binds
    fun bindBuildConfigProvider(
        buildConfigProviderImpl: FrameworkBuildConfigProvider
    ): BuildConfigProvider
}
