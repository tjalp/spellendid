package net.tjalp.spellendid.core.networking

import net.tjalp.spellendid.core.match.Match

/**
 * Track the current state of the player and server
 */
class SpellendidTracker {

    var currentServer: String? = "???"
    var currentMatch: Match? = null
}