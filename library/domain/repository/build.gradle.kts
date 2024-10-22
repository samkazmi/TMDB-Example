plugins {
    id("kotlin")
}

dependencies {
    api(projects.library.domain.model)
    implementation(libs.kotlinx.coroutines)
}
