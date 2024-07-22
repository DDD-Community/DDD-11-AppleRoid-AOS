plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mkung.android.library)
    alias(libs.plugins.mkung.android.library.compose)
}

android {
    namespace = "com.appleroid.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.model)

    api(libs.androidx.metrics)
}