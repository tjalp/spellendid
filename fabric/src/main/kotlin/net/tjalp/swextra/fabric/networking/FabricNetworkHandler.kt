package net.tjalp.swextra.fabric.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import net.tjalp.swextra.core.SwExtra
import net.tjalp.swextra.core.networking.NetworkHandler
import net.tjalp.swextra.core.util.EXECUTOR_SERVICE
import net.tjalp.swextra.fabric.SwExtraFabric
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
            if (SwExtraFabric.platform.isDevelopmentEnvironment || brand.contains("Smash Wizards")) {
                if (!connected) connect()
            }
        }
        ClientPlayConnectionEvents.DISCONNECT.register { _, _ ->
            if (connected) disconnect()
        }
    }

    override fun connect() {
        SwExtra.LOGGER.warn("Connected to Smash Wizards!")
        super.connect()
    }

    override fun disconnect() {
        SwExtra.LOGGER.warn("Disconnected from Smash Wizards!")
        super.disconnect()
    }
}