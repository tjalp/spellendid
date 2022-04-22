package net.tjalp.swextra.core.feature

import com.jagrosh.discordipc.IPCClient
import com.jagrosh.discordipc.IPCListener
import com.jagrosh.discordipc.entities.DiscordBuild
import com.jagrosh.discordipc.entities.RichPresence
import net.tjalp.swextra.core.SwExtra
import net.tjalp.swextra.core.util.EXECUTOR_SERVICE
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

/**
 * A feature that provides rich presence support for Discord.
 */
class RichPresenceFeature {

    private lateinit var client: IPCClient
    private var initialized = false

    /**
     * Initialize the Rich Presence feature
     */
    fun init() {
        SwExtra.LOGGER.info("Initializing Rich Presence feature...")
        try {
            this.client = IPCClient(SwExtra.DISCORD_APP_ID)
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
            SwExtra.LOGGER.error("Failed to initialize Rich Presence feature", ex)
        }
    }

    /**
     * Update the Rich Presence
     */
    fun update() {
        val swExtra = SwExtra.INSTANCE
        val handler = swExtra.networkHandler
        if (!swExtra.config.enableRichPresenceFeature || !handler.connected) {
            this.client.sendRichPresence(null)
            return
        }
        this.client.sendRichPresence(
            RichPresence.Builder()
                .setDetails("In the lobby")
                .setStartTimestamp(OffsetDateTime.ofInstant(Instant.ofEpochSecond(handler.connectTime), ZoneId.systemDefault()))
                .setLargeImage("sw-icon-round")
                .build()
        )
    }
}