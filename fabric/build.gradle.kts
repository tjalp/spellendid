plugins {
    id("fabric-loom")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

repositories {}

dependencies {
    val minecraftVersion: String by project
    minecraft("com.mojang:minecraft:$minecraftVersion")
    val yarnMappings: String by project
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    val loaderVersion: String by project
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    val fabricVersion: String by project
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    val fabricKotlinVersion: String by project
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")

    implementation(project(":core"))
    include(project(":core"))

    val clothConfigApiVersion: String by project
    modApi("me.shedaniel.cloth:cloth-config-fabric:$clothConfigApiVersion") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    val modMenuVersion: String by project
    modImplementation("com.terraformersmc:modmenu:$modMenuVersion")

    val discordIpcVersion: String by project
    include("com.github.jagrosh:DiscordIPC:$discordIpcVersion")

    val jsonVersion: String by project
    include("org.json:json:$jsonVersion")
}

loom {
    runConfigs.configureEach {
        isIdeConfigGenerated = true
    }
}