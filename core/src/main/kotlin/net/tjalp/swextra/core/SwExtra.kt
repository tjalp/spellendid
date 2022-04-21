package net.tjalp.swextra.core

import net.tjalp.swextra.core.feature.RichPresenceFeature
import net.tjalp.swextra.core.platform.Platform
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * The main class of the SwExtra mod
 */
abstract class SwExtra {

    /** The platform that is currently running */
    lateinit var platform: Platform; private set

    /**
     * Initialize the SwExtra core
     */
    fun init() {
        LOGGER.info("Initializing ${this::class.simpleName}...")

        this.platform = initPlatform()

        // This will be removed later on
        LOGGER.info("The current platform name is ${this.platform.platformName}," +
                "development environment is ${this.platform.isDevelopmentEnvironment} " +
                "and the current version is ${this.platform.displayVersion}")

        RichPresenceFeature().init()
    }

    /**
     * Initialize the current platform
     */
    abstract fun initPlatform(): Platform

    companion object {

        /** The mod identifier */
        const val MOD_ID = "swextra"

        /** The current logger */
        val LOGGER: Logger = LogManager.getLogger()
    }
}