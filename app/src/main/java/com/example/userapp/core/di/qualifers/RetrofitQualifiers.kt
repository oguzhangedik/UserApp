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
annotation class RetrofitMainService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAuthService
