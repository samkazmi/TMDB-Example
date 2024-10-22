pluginManagement {
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

rootProject.name = "TMDB-Example"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":library:data:domain")
include(":library:data:locale")
include(":library:data:local")
include(":library:data:remote")
include(":library:domain:model")
include(":library:domain:repository")


