plugins {
    id("java-library")
    alias(libs.plugins.jvm)
    alias(libs.plugins.skycast.async)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}