package com.example.userapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UserAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
