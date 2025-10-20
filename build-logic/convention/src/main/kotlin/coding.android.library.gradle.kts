import com.coding.app.androidExtension
import com.coding.app.configureCoroutineAndroid
import com.coding.app.configureKotest
import com.coding.app.configureKotlinAndroid
import com.coding.app.configureMock
import com.coding.app.findLibrary

plugins {
    id("com.android.library")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(findLibrary("junit4"))
    api(findLibrary("junit.vintage.engine"))
    api(findLibrary("kotlin.test"))
    api(findLibrary("mockk"))
    api(findLibrary("turbine"))
    api(findLibrary("coroutines.test"))
    api(findLibrary("roborazziRule"))

    implementation(findLibrary("roborazzi"))
    implementation(findLibrary("androidx.runner"))
}


configureKotlinAndroid()
configureKotest()
configureMock()
configureCoroutineAndroid()
