package net.tjalp.spellendid.core.networking

import io.netty.buffer.ByteBuf
import net.tjalp.spellendid.core.Spellendid

abstract class SwPacket(
    val buf: ByteBuf
) {

    val spellendid = Spellendid.INSTANCE

    abstract fun handle()
}