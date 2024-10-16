package com.sy.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.sy.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            with(plugins) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
                defaultConfig {
                    minSdk = libs.findVersion("minSdk").get().toString().toInt()
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                extensions.configure<JavaPluginExtension> {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                tasks.withType<KotlinCompile> {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_21.toString()
                    }
                }
            }
        }
    }
}