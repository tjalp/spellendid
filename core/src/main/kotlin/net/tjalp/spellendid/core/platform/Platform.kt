package net.tjalp.spellendid.core.platform

interface Platform {

    /**
     * The mod name
     */
    val name: String

    /**
     * The current platform name
     */
    val platformName: String

    /**
     * Whether the current environment is a development environment
     */
    val isDevelopmentEnvironment: Boolean

    /**
     * The display version of the current platform
     */
    val displayVersion: String

    /**
     * The version of the current platform
     */
    val version: String
}