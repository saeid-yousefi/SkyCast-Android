package com.sy.convention.plugin

import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class KtorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", libs.findLibrary("ktor-core").get())
                add("implementation", libs.findLibrary("ktor-cio").get())
                add("implementation", libs.findLibrary("ktor-serialization").get())
                add("implementation", libs.findLibrary("ktor-logger").get())
            }
        }
    }
}