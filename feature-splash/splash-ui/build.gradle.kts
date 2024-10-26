plugins {
    alias(libs.plugins.skycast.android.library)
    alias(libs.plugins.skycast.compose.library)
    alias(libs.plugins.skycast.di)
    alias(libs.plugins.skycast.test.unit)
    alias(libs.plugins.skycast.test.android)
}

android {
    namespace = "com.sy.splash_ui"
}

dependencies{
    implementation(project(":common-ui"))
}