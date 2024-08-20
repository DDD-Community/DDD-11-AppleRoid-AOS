plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mkung.android.feature.ui)
    alias(libs.plugins.mkung.android.library.compose)
}

android {
    namespace = "com.appleroid.feature.report"
}

dependencies {
    implementation(projects.core.data)
}