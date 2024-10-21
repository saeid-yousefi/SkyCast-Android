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
        register("androidApplicationPlugin") {
            id = "skycast.android.application"
            implementationClass = "com.sy.convention.plugin.AndroidApplicationPlugin"
        }
        register("androidLibraryPlugin") {
            id = "skycast.android.library"
            implementationClass = "com.sy.convention.plugin.AndroidLibraryPlugin"
        }
        register("composePlugin") {
            id = "skycast.compose"
            implementationClass = "com.sy.convention.plugin.ComposePlugin"
        }
        register("UiTestPlugin") {
            id = "skycast.uitest"
            implementationClass = "com.sy.convention.plugin.UiTestPlugin"
        }
        register("UnitTestPlugin") {
            id = "skycast.unittest"
            implementationClass = "com.sy.convention.plugin.UnitTestPlugin"
        }
        register("CoroutinesPlugin") {
            id = "skycast.async"
            implementationClass = "com.sy.convention.plugin.CoroutinesPlugin"
        }
        register("KoinPlugin") {
            id = "skycast.koin"
            implementationClass = "com.sy.convention.plugin.KoinPlugin"
        }
        register("KtorPlugin") {
            id = "skycast.ktor"
            implementationClass = "com.sy.convention.plugin.KtorPlugin"
        }
    }
}