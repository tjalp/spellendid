package net.tjalp.swextra.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.tjalp.swextra.core.feature.RichPresenceFeature
import net.tjalp.swextra.core.platform.Platform
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

/**
 * The main class of the SwExtra mod
 */
abstract class SwExtra {

    /** The platform that is currently running */
    lateinit var platform: Platform; private set

    /** The config file path */
    abstract var configPath: Path

    /** The configuration */
    lateinit var config: SwConfig; private set

    /**
     * Initialize the SwExtra core
     */
    fun init() {
        LOGGER.info("Initializing ${this::class.simpleName}...")

        this.platform = initPlatform()

        // This will be removed later on
        LOGGER.info("The current platform name is ${this.platform.platformName}, " +
                "development environment is ${this.platform.isDevelopmentEnvironment} " +
                "and the current version is ${this.platform.displayVersion}")

        // Initialize the config
        this.config = SwConfig.read(this.configPath)

        RichPresenceFeature().init()
    }

    /**
     * Initialize the current platform
     */
    abstract fun initPlatform(): Platform

    companion object {

        /** The mod identifier */
        const val MOD_ID = "swextra"

        /** The Discord application ID */
        const val DISCORD_APP_ID = 966012623780990977L

        /** The current logger */
        val LOGGER: Logger = LogManager.getLogger()

        /** The main Gson object */
        val GSON: Gson = GsonBuilder().create()

        /** The Gson object that will have pretty printing enabled */
        val GSON_PRETTY: Gson = GsonBuilder().setPrettyPrinting().create()

    }
}