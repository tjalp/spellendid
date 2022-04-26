package net.tjalp.spellendid.core

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.writeText

/**
 * The config of the mod
 */
open class SpellendidConfig {

    var apiKey: String = ""

    var removeLoadingTerrainDelay: Boolean = true
    var removeLoadingTerrainDelayEverywhere: Boolean = true
    var playFallDamageSounds: Boolean = false

    var enableRichPresenceFeature: Boolean = true
    var richPresenceDisplayTime: Boolean = true
    var richPresenceDisplayServer: Boolean = true

    companion object {

        /**
         * Read the config from the config file
         */
        fun read(path: Path): SpellendidConfig {
            if (!path.exists()) return SpellendidConfig()
            val json = path.inputStream().bufferedReader().use { it.readText() }
            return Spellendid.GSON.fromJson(json, SpellendidConfig::class.java)
        }

        /**
         * Save the current config to the config file
         */
        fun save(path: Path, config: SpellendidConfig) {
            val json = Spellendid.GSON_PRETTY.toJson(config)
            path.writeText(json)
        }
    }
}