pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    pluginManagement {
        plugins {
            kotlin("jvm") version "1.4.3"  // Reemplaza "x.x.x" con la versión de Kotlin que estás utilizando
            kotlin("plugin.serialization") version "1.4.3"
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pataVentura"
include(":app")
