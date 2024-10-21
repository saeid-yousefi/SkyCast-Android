pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
        }
    }
}


rootProject.name = "SkyCast"
include(":app")
includeBuild("build-logic")
