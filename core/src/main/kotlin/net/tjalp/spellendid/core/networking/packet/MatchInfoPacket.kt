package net.tjalp.spellendid.core.networking.packet

import io.netty.buffer.ByteBuf
import net.tjalp.spellendid.core.match.Match
import net.tjalp.spellendid.core.networking.SwPacket

/**
 * The match info packet contains data from the current game, is usually only sent once per match
 */
class MatchInfoPacket(private val buf: ByteBuf) : SwPacket() {

    override fun handle() {
        // Get length from first char sequence from netty buffer
        val length = buf.readInt()

        val matchType = buf.readCharSequence(length, Charsets.UTF_8).toString()
        val currentPlayers = buf.readInt()
        val maxPlayers = buf.readInt()

        spellendid.tracker.currentMatch = Match(matchType, currentPlayers, maxPlayers)
    }
}