plugins {
    alias(libs.plugins.mkung.android.library)
    alias(libs.plugins.mkung.android.hilt)
}

android {
    namespace = "com.appleroid.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.network)
    api(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}