package net.tjalp.swextra.core

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.writeText

/**
 * The config of the mod
 */
open class SwConfig {

    var enableRichPresenceFeature: Boolean = true

    var removeLoadingTerrainDelay: Boolean = true

    var removeLoadingTerrainDelayEverywhere: Boolean = true

    companion object {

        /**
         * Read the config from the config file
         */
        fun read(path: Path): SwConfig {
            if (!path.exists()) return SwConfig()
            val json = path.inputStream().bufferedReader().use { it.readText() }
            return SwExtra.GSON.fromJson(json, SwConfig::class.java)
        }

        /**
         * Save the current config to the config file
         */
        fun save(path: Path, config: SwConfig) {
            val json = SwExtra.GSON_PRETTY.toJson(config)
            path.writeText(json)
        }
    }
}