package net.tjalp.swextra.core

import net.tjalp.swextra.core.platform.Platform
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * The main class of the SwExtra mod
 */
abstract class SwExtra {

    /** The current logger */
    val logger: Logger = LogManager.getLogger()

    /** The platform that is currently running */
    lateinit var platform: Platform; private set

    /**
     * Initialize the SwExtra core
     */
    fun init() {
        this.platform = initPlatform()

        logger.info("The current platform name is ${this.platform.platformName}, development environment is ${this.platform.isDevelopmentEnvironment}")
    }

    /**
     * This method is for some misc stuff that has
     * to be done after the core has been initialized
     */
    open fun postInit() {

    }

    /**
     * Initialize the current platform
     */
    abstract fun initPlatform(): Platform
}