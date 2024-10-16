package com.sy.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(plugins) {
                apply(libs.findPlugin("compose-compiler").get().toString())
            }
            extensions.configure<ApplicationExtension> {
                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("composeCompiler").get().toString()
                }
            }
            dependencies {
                add("implementation", libs.findLibrary("androidx-activity-compose").get())
                add("implementation", platform(libs.findLibrary("androidx-activity-compose").get()))
                add("implementation", libs.findLibrary("androidx-ui").get())
                add("implementation", libs.findLibrary("libs.androidx.ui.graphics").get())
                add("implementation", libs.findLibrary("androidx-ui-tooling").get())
                add("implementation", libs.findLibrary("libs.androidx.material3").get())
            }
        }
    }
}