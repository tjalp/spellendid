package net.tjalp.spellendid.core.networking.packet

import io.netty.buffer.ByteBuf
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.networking.SwPacket

/**
 * The handshake packet stating that this is Smash Wizards
 */
class HandshakePacket : SwPacket() {

    private val networkHandler: NetworkHandler<*> = spellendid.networkHandler

    override fun handle() {
        if (!networkHandler.connected) networkHandler.connect()
    }
}