plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.app.data.local"
    compileSdk = Configs.AndroidVersion.compile
    kotlin.jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

dependencies {
    // Service locator
    implementation(libs.koin.android)
    implementation(projects.library.domain.model)
    implementation(projects.library.data.locale)

    // Data storage and serialization
    implementation(libs.kotlinx.serialization.json)

    // Testing
    testCompileOnly(libs.koin.test)
    testCompileOnly(libs.koin.test.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
