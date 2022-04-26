plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

dependencies {
    val log4jVersion: String by project
    api("org.apache.logging.log4j:log4j-api:$log4jVersion")
    api("org.apache.logging.log4j:log4j-core:$log4jVersion")
    val discordIpcVersion: String by project
    api("com.github.jagrosh:DiscordIPC:$discordIpcVersion")
    val gsonVersion: String by project
    api("com.google.code.gson:gson:$gsonVersion")
    val jsonVersion: String by project
    api("org.json:json:$jsonVersion")
    val nettyVersion: String by project
    api("io.netty:netty-all:$nettyVersion")
}