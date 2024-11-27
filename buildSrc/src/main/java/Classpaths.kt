object Classpaths {
    const val gradleClasspath =
        "com.android.tools.build:gradle:${Versions.Gradle.gradleVersion}"
    const val kotlinGradleClasspath =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlinVersion}"
    const val safeVarargs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Gradle.safeArgs}"
    const val kotlinSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.kotlinVersion}"
    const val hiltGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.Gradle.hiltVersion}"
   const val googleServices =
        "com.google.gms:google-services:${Versions.GoogleFirebase.googleServices}"
    const val crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.GoogleFirebase.firebaseCrashPlugin}"
}
