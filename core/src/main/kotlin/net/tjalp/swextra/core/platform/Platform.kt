package net.tjalp.swextra.core.platform

interface Platform {

    /**
     * The current platform name
     */
    val platformName: String

    /**
     * Whether the current environment is a development environment
     */
    val isDevelopmentEnvironment: Boolean
}