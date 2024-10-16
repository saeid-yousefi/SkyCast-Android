package com.sy.convention.plugin

import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class UiTestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                add(
                    "androidTestImplementation",
                    platform(libs.findLibrary("androidx-compose-bom").get())
                )
                add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
            }
        }
    }
}