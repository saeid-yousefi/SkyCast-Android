plugins {
    alias(libs.plugins.skycast.android.library)
    alias(libs.plugins.skycast.test.unit)
}

android {
    namespace = "com.sy.spash_domain"

}

dependencies {
    api(project(":common-domain"))
}