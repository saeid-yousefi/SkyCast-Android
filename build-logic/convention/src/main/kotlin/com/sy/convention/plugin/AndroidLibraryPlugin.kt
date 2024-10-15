package com.sy.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.plugins.apply("com.android.library")
        project.plugins.apply("kotlin-android")

        project.extensions.configure<ApplicationExtension> {
            compileSdk = project.libs.findVersion("compileSdk").get().toString().toInt()
            defaultConfig {
                minSdk = project.libs.findVersion("minSdk").get().toString().toInt()
                targetSdk = project.libs.findVersion("targetSdk").get().toString().toInt()
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
            project.tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = project.libs.findVersion("jvmTarget").get().toString()
                }
            }
        }
    }
}