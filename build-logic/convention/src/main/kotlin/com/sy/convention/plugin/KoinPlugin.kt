package com.sy.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class KoinPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", libs.findLibrary("koin-android").get())
                add("implementation", libs.findLibrary("koin-androidx-compose").get())
                add("implementation", libs.findLibrary("koin-androidx-viewmodel").get())
            }
        }
    }
}