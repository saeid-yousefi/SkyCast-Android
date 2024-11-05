package com.sy.convention.plugin

import com.sy.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure


class KotlinLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            with(plugins) {
                apply(libs.findPlugin("java-library").get().get().pluginId)
                apply(libs.findPlugin("jvm").get().get().pluginId)
            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }
    }
}