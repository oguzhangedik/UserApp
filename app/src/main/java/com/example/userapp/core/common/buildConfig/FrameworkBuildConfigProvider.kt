package com.example.userapp.core.common.buildConfig

import com.example.userapp.BuildConfig
import com.example.userapp.core.common.build.BuildConfigProvider

class FrameworkBuildConfigProvider: BuildConfigProvider {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}
