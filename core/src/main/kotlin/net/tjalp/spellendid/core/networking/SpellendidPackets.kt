package net.tjalp.spellendid.core.networking

val HANDSHAKE = register("handshake")
val MATCH_INFO = register("match_info")

private fun register(path: String): String = "smashwizards:$path"