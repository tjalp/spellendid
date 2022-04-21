plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

dependencies {
    val log4jVersion: String by project
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    val discordIpcVersion: String by project
    implementation("com.github.jagrosh:DiscordIPC:$discordIpcVersion")
}