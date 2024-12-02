package com.example.userapp.data.di.qualifers

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProjectOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAppService
