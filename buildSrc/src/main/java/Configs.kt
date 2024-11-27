object Configs {
    const val applicationId = "com.example.userapp"
    const val nameSpace = "com.example.userapp"
    const val minSdkVersion = 24
    const val compileSdkVersion = 35
    const val targetSdkVersion = 34

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val versionCode = 1
    val versionName = calculateVersionName()
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun calculateVersionName(): String = "$versionMajor.$versionMinor.$versionPatch"
}
