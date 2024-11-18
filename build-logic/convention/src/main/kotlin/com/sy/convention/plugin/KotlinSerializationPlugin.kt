package com.sy.convention.plugin

import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class KotlinSerializationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(plugins) {
                apply(libs.findPlugin("kotlinx-serialization").get().get().pluginId)
            }
            dependencies {
                add("implementation", libs.findLibrary("kotlinx-serialization").get())
            }
        }
    }
}