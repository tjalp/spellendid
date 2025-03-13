package net.tjalp.spellendid.fabric.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.toast.SystemToast
import net.minecraft.text.Text.translatable
import net.minecraft.util.Identifier
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.networking.packet.HandshakePacket
import net.tjalp.spellendid.core.util.EXECUTOR_SERVICE
import net.tjalp.spellendid.fabric.SpellendidFabric
import java.util.concurrent.TimeUnit

class FabricNetworkHandler : NetworkHandler<Identifier>() {

    override fun setup() {
        super.setup()

        // Development
        ClientPlayConnectionEvents.JOIN.register { _, _, _ ->
            if (SpellendidFabric.platform.isDevelopmentEnvironment) {
                connect()
            }
        }

        // TODO - Make a separate class for every packet

        PayloadTypeRegistry.playS2C().register(ClientboundHandshakePacket.ID, ClientboundHandshakePacket.CODEC)

        // Handshake to connect
        ClientPlayNetworking.registerGlobalReceiver(ClientboundHandshakePacket.ID) { payload, context ->
            HandshakePacket().handle()
        }

        // Disconnection
        ClientPlayConnectionEvents.DISCONNECT.register { _, _ ->
            if (connected) disconnect()
        }

//        // Match info packet
//        ClientPlayNetworking.registerGlobalReceiver(Identifier(MATCH_INFO)) { _, _, buf, _ ->
//            MatchInfoPacket(buf).handle()
//        }
//
//        // Server info packet
//        ClientPlayNetworking.registerGlobalReceiver(Identifier(SERVER_INFO)) { _, _, buf, _ ->
//            ServerInfoPacket(buf).handle()
//        }
    }

    override fun connect() {
        Spellendid.LOGGER.warn("Connected to Smash Wizards!")
        super.connect()

        EXECUTOR_SERVICE.schedule({
            if (connected) {
                SystemToast.add(
                    MinecraftClient.getInstance().toastManager,
                    SystemToast.Type.PERIODIC_NOTIFICATION,
                    translatable("toast.spellendid.connected.line1"),
                    translatable("toast.spellendid.connected.line2"),
                )
            }
        }, 2L, TimeUnit.SECONDS)
    }

    override fun disconnect() {
        Spellendid.LOGGER.warn("Disconnected from Smash Wizards!")
        super.disconnect()
    }
}