plugins {
    id("skycast.android.library")
    id("skycast.unittest")
}

android {
    namespace = "com.sy.async"
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

