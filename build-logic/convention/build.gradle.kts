import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    compileOnly(libs.gradleAndroid)
    compileOnly(libs.koltinGradlePlugin)
    compileOnly(libs.kspGradlePlugin)
}
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "skycast.android.application"
            implementationClass = "com.sy.convention.plugin.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "skycast.android.library"
            implementationClass = "com.sy.convention.plugin.AndroidLibraryPlugin"
        }
    }
}