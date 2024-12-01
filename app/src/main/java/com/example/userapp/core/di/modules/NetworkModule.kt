package com.example.userapp.core.di.modules


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.userapp.core.common.build.BuildConfigProvider
import com.example.userapp.core.common.buildConfig.FrameworkBuildConfigProvider
import com.example.userapp.core.common.dataStore.AppDataStore
import com.example.userapp.core.di.qualifers.ProjectOkHttpClient
import com.example.userapp.core.di.qualifers.ProjectOkHttpClientWithAuthentication
import com.example.userapp.core.di.qualifers.RetrofitAuthService
import com.example.userapp.core.di.qualifers.RetrofitAppService
import com.example.userapp.core.netwok.MainRestHeadersInterceptor
import com.example.userapp.core.netwok.interceptor.AuthenticationInterceptor
import com.example.userapp.core.netwok.interceptor.HttpRequestInterceptor
import com.example.userapp.core.netwok.retrofit.NetworkConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ProjectOkHttpClientWithAuthentication
    @Provides
    @Singleton
    fun provideOkHttpClientWithAuthentication(
        @ApplicationContext appContext: Context,
        networkConfig: NetworkConfig,
        loggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor(networkConfig))
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(ChuckerInterceptor(appContext))
            .callTimeout(networkConfig.getDefaultCallTimeoutMillis(), TimeUnit.MILLISECONDS)
            .connectTimeout(networkConfig.getDefaultConnectTimeoutMillis(), TimeUnit.MILLISECONDS)
            .readTimeout(networkConfig.getDefaultReadTimeoutMillis(), TimeUnit.MILLISECONDS)
            .writeTimeout(networkConfig.getDefaultWriteTimeoutMillis(), TimeUnit.MILLISECONDS)
            .build()
    }

    @RetrofitAppService
    @Provides
    @Singleton
    fun provideRetrofitForAppService(
        networkConfig: NetworkConfig,
        @ProjectOkHttpClientWithAuthentication okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(networkConfig.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    @ProjectOkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
        networkConfig: NetworkConfig,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor(networkConfig))
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor(appContext))
            .callTimeout(networkConfig.getDefaultCallTimeoutMillis(), TimeUnit.MILLISECONDS)
            .connectTimeout(networkConfig.getDefaultConnectTimeoutMillis(), TimeUnit.MILLISECONDS)
            .readTimeout(networkConfig.getDefaultReadTimeoutMillis(), TimeUnit.MILLISECONDS)
            .writeTimeout(networkConfig.getDefaultWriteTimeoutMillis(), TimeUnit.MILLISECONDS)
            .build()
    }

    @RetrofitAuthService
    @Provides
    @Singleton
    fun provideRetrofitForAuthService(
        networkConfig: NetworkConfig,
        @ProjectOkHttpClient okHttpClient: OkHttpClient,
        moshi: Moshi): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(networkConfig.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(buildConfig: BuildConfigProvider): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (buildConfig.isDebug()) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(appDataStore: AppDataStore): AuthenticationInterceptor =
        AuthenticationInterceptor(appDataStore = appDataStore)

    @Provides
    @Singleton
    fun provideMainRestHeadersInterceptor(): MainRestHeadersInterceptor =
        MainRestHeadersInterceptor()

    @Provides
    @Singleton
    fun provideFrameworkBuildConfigProvider(): FrameworkBuildConfigProvider =
        FrameworkBuildConfigProvider()
}
