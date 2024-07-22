plugins {
    alias(libs.plugins.mkung.android.library)
    alias(libs.plugins.mkung.android.retrofit)
    alias(libs.plugins.mkung.android.hilt)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.appleroid.core.network"
}
