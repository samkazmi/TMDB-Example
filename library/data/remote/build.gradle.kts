plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.app.data.remote"
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
    // Project
    implementation(projects.library.domain.model)
    implementation(projects.library.data.locale)

    // Service locator
    implementation(libs.koin.core)

    // Annotations
    api(libs.androidx.annotation)

    // Networking and serialization
    api(libs.ktor.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.contentNegotioation)
    implementation(libs.ktor.json)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.mock)
    implementation(libs.ktor.okhttp)

    // Testing
    testCompileOnly(libs.koin.test)
    testCompileOnly(libs.koin.test.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
