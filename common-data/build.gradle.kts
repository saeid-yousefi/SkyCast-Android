plugins {
    alias(libs.plugins.skycast.kotlin.library)
    alias(libs.plugins.skycast.network)
}
dependencies{
    implementation(project(":common-domain"))
}