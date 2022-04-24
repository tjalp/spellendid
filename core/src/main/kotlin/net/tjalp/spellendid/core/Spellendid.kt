package net.tjalp.spellendid.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.tjalp.spellendid.core.feature.RichPresenceFeature
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.platform.Platform
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

/**
 * The main class of the Spellendid mod
 */
abstract class Spellendid {

    /** The platform that is currently running */
    lateinit var platform: Platform; private set

    /** The config file path */
    abstract var configPath: Path

    /** The configuration */
    lateinit var config: SpellendidConfig; private set

    abstract var networkHandler: NetworkHandler<*>

    /**
     * Initialize the Spellendid core
     */
    fun init() {
        LOGGER.info("Initializing ${this::class.simpleName}...")
        INSTANCE = this

        this.platform = initPlatform()

        // Initialize the config
        this.config = SpellendidConfig.read(this.configPath)

        RichPresenceFeature().init()
    }

    /**
     * Initialize the current platform
     */
    abstract fun initPlatform(): Platform

    companion object {

        /** The mod identifier */
        const val MOD_ID = "spellendid"

        /** The Discord application ID */
        const val DISCORD_APP_ID = 966012623780990977L

        /** The current logger */
        val LOGGER: Logger = LogManager.getLogger()

        /** The main Gson object */
        val GSON: Gson = GsonBuilder().create()

        /** The Gson object that will have pretty printing enabled */
        val GSON_PRETTY: Gson = GsonBuilder().setPrettyPrinting().create()

        /** The current instance of the Spellendid class */
        lateinit var INSTANCE: Spellendid

    }
}