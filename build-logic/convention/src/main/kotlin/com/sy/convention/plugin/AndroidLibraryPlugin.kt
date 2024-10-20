package com.sy.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.sy.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            with(plugins) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
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
                dependencies {
                    add("implementation", libs.findLibrary("androidx-core-ktx").get())
                    add(
                        "implementation",
                        libs.findLibrary("androidx-lifecycle-runtime-ktx").get()
                    )
                }
            }
        }
    }
}