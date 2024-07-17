pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MKung"
include(":app")
include(":core:common")
include(":core:data")
include(":core:network")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:ui")
include(":feature:home")
include(":feature:ask")
include(":feature:mypage")
