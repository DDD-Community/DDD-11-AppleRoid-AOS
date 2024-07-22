plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mkung.android.library.compose)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.appleroid.core.designsystem"
}

dependencies {
    api(libs.bundles.compose)
}