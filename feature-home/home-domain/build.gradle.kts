plugins {
    alias(libs.plugins.skycast.kotlin.library)
    alias(libs.plugins.skycast.async)
    alias(libs.plugins.skycast.serialization)
}
dependencies{
    api(project(":common-domain"))
}
dependencies {
    implementation(libs.kotlinx.datetime)
}