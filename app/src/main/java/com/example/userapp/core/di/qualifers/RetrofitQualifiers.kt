package com.example.userapp.core.di.qualifers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProjectOkHttpClientWithAuthentication

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProjectOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAppService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAuthService
