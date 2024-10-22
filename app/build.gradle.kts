plugins {
    alias(libs.plugins.skycast.android.application)
    alias(libs.plugins.skycast.compose.application)
    alias(libs.plugins.skycast.async)
    alias(libs.plugins.skycast.di)
    alias(libs.plugins.skycast.network)
    alias(libs.plugins.skycast.test.android)
    alias(libs.plugins.skycast.test.unit)
}

android {
    namespace = "com.sy.skycast"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(project(":common-ui"))
    implementation(libs.datastore)
}
