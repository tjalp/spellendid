package net.tjalp.spellendid.core.match

class Match(
    val type: Type,
    var currentPlayers: Int,
    var maxPlayers: Int
) {

    enum class Type(
        val friendlyName: String
    ) {
        FFA("FFA"),
        UNKNOWN("???");

        companion object {

            /**
             * Get the type of the match from the string
             *
             * @param string The string to match the type from
             */
            fun fromString(string: String): Type {
                for (type in values()) {
                    if (type.name.equals(string, true)) {
                        return type
                    }
                }
                return UNKNOWN
            }
        }
    }
}