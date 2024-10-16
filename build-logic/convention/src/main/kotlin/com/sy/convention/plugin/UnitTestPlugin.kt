package com.sy.convention.plugin

import com.sy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class UnitTestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}