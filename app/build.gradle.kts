plugins {
    id("skycast.android.application")
    id("skycast.compose")
    id("skycast.uitest")
    id("skycast.unittest")
}

android {
    namespace = "com.sy.skycast"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
