package com.example.userapp.data.di.modules


import com.example.userapp.data.remote.AppService
import com.example.userapp.data.di.qualifers.RetrofitAppService
import com.example.userapp.core.netwok.retrofit.NetworkConfig
import com.example.userapp.core.netwok.retrofit.NetworkConfigImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppNetworkModule {

    @Binds
    fun bindNetworkConfig(networkConfigImpl: NetworkConfigImpl): NetworkConfig


    companion object {

        @Provides
        @Singleton
        fun provideAppService(@RetrofitAppService retrofit: Retrofit): AppService =
            retrofit.create(AppService::class.java)
    }
}
