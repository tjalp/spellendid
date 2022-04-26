package net.tjalp.spellendid.core.networking.packet

import io.netty.buffer.ByteBuf
import net.tjalp.spellendid.core.networking.SwPacket

class ServerInfoPacket(buf: ByteBuf) : SwPacket(buf) {

    override fun handle() {
        // Get length from first char sequence from netty buffer
        val length = buf.readInt()

        val serverIdentity = buf.readCharSequence(length, Charsets.UTF_8).toString()

        spellendid.tracker.currentServer = serverIdentity
        spellendid.tracker.currentMatch = null
    }
}