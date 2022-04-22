package net.tjalp.swextra.fabric.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.util.Identifier
import net.tjalp.swextra.core.SwExtra
import net.tjalp.swextra.core.networking.NetworkHandler
import net.tjalp.swextra.fabric.SwExtraFabric

class FabricNetworkHandler : NetworkHandler<Identifier>() {

    override val channel: Identifier = Identifier("smashwizards", "extra")

    override fun setup() {
        super.setup()

        ClientPlayNetworking.registerGlobalReceiver(Identifier(Identifier.DEFAULT_NAMESPACE, "brand")) {
                _, _, buf, _ ->
            val brand = buf.readString()
            if (SwExtraFabric.platform.isDevelopmentEnvironment || brand.contains("Smash Wizards")) {
                connect()
            }
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