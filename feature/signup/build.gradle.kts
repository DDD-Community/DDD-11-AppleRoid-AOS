plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mkung.android.feature.ui)
    alias(libs.plugins.mkung.android.library.compose)
}

android {
    namespace = "com.appleroid.feature.signup"
}

dependencies {
    implementation(projects.core.data)
}