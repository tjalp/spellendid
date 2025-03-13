package net.tjalp.spellendid.fabric.networking

import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import net.tjalp.spellendid.core.networking.HANDSHAKE

object ClientboundHandshakePacket : CustomPayload {
    val ID = CustomPayload.Id<ClientboundHandshakePacket>(Identifier.of(HANDSHAKE))
    val CODEC = PacketCodec.unit<RegistryByteBuf, ClientboundHandshakePacket>(ClientboundHandshakePacket)

    override fun getId(): CustomPayload.Id<out CustomPayload> {
        return ID
    }
}