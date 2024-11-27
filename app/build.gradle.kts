plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("kotlin-parcelize")
    id("org.sonarqube") version "4.4.1.3373"
}

android {
    namespace = Configs.nameSpace
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStdLib)
    implementation(Dependencies.Kotlin.kotlinReflect)

    // Android
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.androidCoreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.legacySupport)
    implementation(Dependencies.Android.multidex)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Android.fragment)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.cardView)

    // Coroutines
    implementation(Dependencies.Coroutines.kotlinCoroutinesCore)
    implementation(Dependencies.Coroutines.kotlinCoroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesTest)
    implementation(Dependencies.Coroutines.kotlinCoroutinesAdapter)

    // Navigation
    implementation(Dependencies.Navigation.runTimeNavigation)
    implementation(Dependencies.Navigation.navigationFragment)
    implementation(Dependencies.Navigation.navigationUi)
    implementation(Dependencies.Navigation.navigationDynamic)

    // LifeCycle
    implementation(Dependencies.LifeCycle.runTimeLifeCycle)
    implementation(Dependencies.LifeCycle.lifeCycleCompiler)
    implementation(Dependencies.LifeCycle.liveData)
    implementation(Dependencies.LifeCycle.viewModel)
    implementation(Dependencies.LifeCycle.lifeCycleCommon)

    //DataStore
    implementation(Dependencies.DataStore.dataStore)

    // Dagger-Hilt
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)

    // Network
    implementation(Dependencies.Network.gson)
    implementation(Dependencies.Network.gsonAdapter)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.rxJavaAdapter)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.loggingInterceptor)

    // Moshi
    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.retrofitMoshi)

    //JWT-Auth0
    implementation(Dependencies.Network.jwt)

    // Glide
    implementation(Dependencies.Glide.glide)
    annotationProcessor(Dependencies.Glide.glideCompiler)

    //Timber
    implementation(Dependencies.Tools.timber)

    // Testing
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.truthExt)
    testImplementation(Dependencies.Test.mockK)
    testImplementation(Dependencies.Test.coreTesting)
    testImplementation(Dependencies.Test.androidJunit)
    testImplementation(Dependencies.Test.espressoCore)
    testImplementation(Dependencies.DI.hiltAndroidTesting)
    implementation(Dependencies.Test.monitor)
    implementation(Dependencies.Test.junitKtx)
    androidTestImplementation(Dependencies.Test.testng)

    // Dialog
    implementation(Dependencies.Dialogs.dialogCore)
    implementation(Dependencies.Dialogs.dateTime)

    // Room
    implementation(Dependencies.Room.runtime)
    kapt(Dependencies.Room.compiler)

    // Paging
    implementation(Dependencies.Paging.paging)

    // Logto
    implementation(Dependencies.Logto.logto)

    //Chucker
    debugImplementation(Dependencies.Chucker.chucker)
    releaseImplementation(Dependencies.Chucker.chuckerNoOp)

    //Flexbox
    implementation(Dependencies.Flexbox.flexbox)

    //Coil (https://github.com/coil-kt/coil)
    implementation(Dependencies.Coil.coil)
    implementation(Dependencies.Coil.coilSvg)

    implementation(Dependencies.ViewPager.viewPager)
    implementation(Dependencies.ViewPager.viewPagerIndicator)

}