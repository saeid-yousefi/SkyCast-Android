plugins {
    alias(libs.plugins.skycast.android.library)
    alias(libs.plugins.skycast.test.unit)
    alias(libs.plugins.skycast.datastore)
    alias(libs.plugins.skycast.network)
    alias(libs.plugins.skycast.di)
}

android {
    namespace = "com.sy.home_data"
}