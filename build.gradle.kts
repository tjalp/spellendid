plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

allprojects {
    val modVersion: String by project
    version = modVersion
    val mavenGroup: String by project
    group = mavenGroup
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    base {
        val archivesBaseName: String by project
        archivesName.set(archivesBaseName + "-" + this.archivesName.get())
    }

    tasks {
        val javaVersion = JavaVersion.VERSION_17
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            sourceCompatibility = javaVersion.toString()
            targetCompatibility = javaVersion.toString()
            options.release.set(javaVersion.toString().toInt())
        }
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions { jvmTarget = javaVersion.toString() }
            sourceCompatibility = javaVersion.toString()
            targetCompatibility = javaVersion.toString()
        }
        jar { from("LICENSE") { rename { "${it}_${base.archivesName}" } } }
        processResources {
            inputs.property("version", project.version)
            filesMatching("fabric.mod.json") { expand(mutableMapOf("version" to project.version)) }
        }
        java {
            toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersion.toString())) }
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
            withSourcesJar()
        }
    }

    repositories {
        maven("https://maven.fabricmc.net") { name = "Fabric" }
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io") { name = "Jitpack" }
    }
}