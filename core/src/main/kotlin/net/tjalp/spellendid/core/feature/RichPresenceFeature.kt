package net.tjalp.spellendid.core.feature

import com.jagrosh.discordipc.IPCClient
import com.jagrosh.discordipc.IPCListener
import com.jagrosh.discordipc.entities.DiscordBuild
import com.jagrosh.discordipc.entities.RichPresence
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.util.EXECUTOR_SERVICE
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A feature that provides rich presence support for Discord.
 */
class RichPresenceFeature {

    private lateinit var client: IPCClient
    private var initialized = false
    private val config = Spellendid.INSTANCE.config

    /**
     * Initialize the Rich Presence feature
     */
    fun init() {
        Spellendid.LOGGER.info("Initializing Rich Presence feature...")
        try {
            this.client = IPCClient(Spellendid.DISCORD_APP_ID)
            this.client.setListener(object : IPCListener {
                override fun onReady(client: IPCClient) {
                    this@RichPresenceFeature.initialized = true
                    EXECUTOR_SERVICE.scheduleAtFixedRate({
                        update()
                    }, 0L, 1L, TimeUnit.SECONDS)
                }
            })
            this.client.connect(DiscordBuild.ANY)
        } catch (ex: Exception) {
            Spellendid.LOGGER.error("Failed to initialize Rich Presence feature", ex)
        }
    }

    /**
     * Update the Rich Presence
     */
    fun update() {
        val spellendid = Spellendid.INSTANCE
        val handler = spellendid.networkHandler
        if (!config.enableRichPresenceFeature || !handler.connected) {
            this.client.sendRichPresence(null)
            return
        }

        this.client.sendRichPresence(
            RichPresence.Builder().apply {
                setDetails("play.smashwizards.net")
                if (config.richPresenceDisplayServer) setState("Connected to FFA-3")
                setParty(UUID.randomUUID().toString(), 1, 4)
                if (config.richPresenceDisplayTime) setStartTimestamp(OffsetDateTime.ofInstant(Instant.ofEpochSecond(handler.connectTime), ZoneId.systemDefault()))
                setLargeImage("sw-icon-fancy")
            }.build()
        )
    }
}