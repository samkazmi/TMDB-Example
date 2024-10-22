
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.app.data.locale"
    compileSdk = Configs.AndroidVersion.compile
    defaultConfig {
        minSdk = Configs.AndroidVersion.minimum
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
    }
    kotlin.jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appCompat)
    implementation(libs.koin.core)
}
