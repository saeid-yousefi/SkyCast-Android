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
include(":feature-onboarding:onboarding-ui")
include(":common-ui")
include(":feature-onboarding:onboarding-data")
include(":feature-onboarding:onboarding-domain")
