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
include(":common-domain")
include(":feature-splash:splash-ui")
include(":feature-splash:splash-data")
include(":feature-splash:splash-domain")
include(":feature-home:home-ui")
include(":feature-home:home-data")
include(":feature-home:home-domain")
