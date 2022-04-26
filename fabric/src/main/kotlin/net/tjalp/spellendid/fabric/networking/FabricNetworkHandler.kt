package net.tjalp.spellendid.fabric.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.util.Identifier
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.networking.HANDSHAKE
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.fabric.SpellendidFabric

class FabricNetworkHandler : NetworkHandler<Identifier>() {

    override fun setup() {
        super.setup()

        // Development
        ClientPlayConnectionEvents.JOIN.register { _, _, _ ->
            if (SpellendidFabric.platform.isDevelopmentEnvironment) {
                connect()
            }
        }

        // TODO - Make a seperate class for every packet

        // Handshake to connect
        ClientPlayNetworking.registerGlobalReceiver(Identifier(HANDSHAKE)) { _, _, _, _ ->
            if (!connected) connect()
        }

        // Disconnection
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