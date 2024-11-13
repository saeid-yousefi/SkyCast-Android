plugins {
    id("java-library")
    alias(libs.plugins.jvm)
    alias(libs.plugins.skycast.async)
    alias(libs.plugins.skycast.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}