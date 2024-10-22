plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.app.data.domain"
    compileSdk = Configs.AndroidVersion.compile
    kotlin.jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

dependencies {
    // Project
    api(projects.library.domain.repository)
    implementation(projects.library.data.remote)
    implementation(projects.library.data.local)

    // Service locator
    implementation(libs.koin.android)

    // Testing
    testCompileOnly(libs.koin.test)
    testCompileOnly(libs.koin.test.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
