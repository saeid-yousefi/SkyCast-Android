package com.sy.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.configureSdk() {
    configure<ApplicationExtension> {
        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()
            targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
        }
    }
}

internal fun Project.configureCompileOptions() {
    configure<ApplicationExtension> {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_21.toString()
            }
        }
    }
}

internal fun Project.configureBuildTypes() {
    configure<ApplicationExtension> {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
            }
        }
    }
}