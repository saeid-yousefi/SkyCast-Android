package com.sy.convention.plugin

import com.sy.convention.configureBuildTypes
import com.sy.convention.configureCompileOptions
import com.sy.convention.configureSdk
import org.gradle.api.Plugin
import org.gradle.api.Project


class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(plugins) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            configureSdk()
            configureCompileOptions()
            configureBuildTypes()
        }
    }
}