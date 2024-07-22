plugins {
    alias(libs.plugins.mkung.android.library)
}

android {
    namespace = "com.appleroid.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}