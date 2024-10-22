plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = Configs.packageId
    compileSdk = Configs.AndroidVersion.compile
    kotlin.jvmToolchain(libs.versions.jvmTarget.get().toInt())
    android.buildFeatures.buildConfig = true
    defaultConfig {
        applicationId = Variables.applicationId
        minSdk = Configs.AndroidVersion.minimum
        targetSdk = Configs.AndroidVersion.target
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // NOTE: Ideal way would be to create flavors and add different config for each flavor
        buildConfigField("String", "BASE_URL", Variables.Staging.baseUrl)
        buildConfigField("String", "IMAGE_BASE_URL", Variables.Staging.imageBaseUrl)
        buildConfigField("String", "API_KEY", Variables.Staging.apiKey)
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
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    implementation(projects.library.data.domain)
    implementation(projects.library.data.local)
    implementation(projects.library.data.locale)
    implementation(projects.library.data.remote)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.appCompat)
    implementation(libs.material)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
    // Testing
    androidTestImplementation(libs.google.truth)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.turbine)
    testCompileOnly(libs.koin.test)
    testCompileOnly(libs.koin.test.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}