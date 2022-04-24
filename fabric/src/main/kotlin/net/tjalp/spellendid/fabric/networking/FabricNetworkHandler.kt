package net.tjalp.spellendid.fabric.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.util.EXECUTOR_SERVICE
import net.tjalp.spellendid.fabric.SpellendidFabric
import java.util.concurrent.TimeUnit

class FabricNetworkHandler : NetworkHandler<Identifier>() {

    override val channel: Identifier = Identifier("smashwizards", "extra")

    override fun setup() {
        super.setup()

        ClientPlayNetworking.registerGlobalReceiver(Identifier(Identifier.DEFAULT_NAMESPACE, "brand")) {
                _, _, buf, _ ->
            val brand = buf.readString()
            // Fix brand showing up as "null"
            EXECUTOR_SERVICE.schedule({
                MinecraftClient.getInstance().player?.serverBrand = brand
            }, 1L, TimeUnit.SECONDS)
            // TODO - Improve the way Smash Wizards is detected
            if (SpellendidFabric.platform.isDevelopmentEnvironment || brand.contains("Smash Wizards")) {
                if (!connected) connect()
            }
        }
        ClientPlayConnectionEvents.DISCONNECT.register { _, _ ->
            if (connected) disconnect()
        }
    }

    override fun connect() {
        Spellendid.LOGGER.warn("Connected to Smash Wizards!")
        super.connect()
    }

    override fun disconnect() {
        Spellendid.LOGGER.warn("Disconnected from Smash Wizards!")
        super.disconnect()
    }
}