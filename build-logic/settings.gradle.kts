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
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
 