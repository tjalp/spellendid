package net.tjalp.spellendid.core.match

class Match(
    val type: Type,
    var currentPlayers: Int,
    var maxPlayers: Int
) {

    enum class Type {
        FFA
    }
}