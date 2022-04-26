package net.tjalp.spellendid.core.networking

val HANDSHAKE = register("handshake")
val MATCH_INFO = register("match_info")
val SERVER_INFO = register("server_info")

private fun register(path: String): String = "smashwizards:$path"