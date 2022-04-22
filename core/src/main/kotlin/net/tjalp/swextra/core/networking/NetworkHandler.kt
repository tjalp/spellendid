package net.tjalp.swextra.core.networking

/**
 * The networking handler which handles all the networking.
 * It will keep track of whether we're currently connected
 * to the Smash Wizards server and some other stuff.
 *
 * Methods are to be called manually depending on the platform
 */
abstract class NetworkHandler<T> {

    /** Whether we're currently connected to the Smash Wizards server */
    var connected: Boolean = false; private set

    /** The connect time **/
    var connectTime: Long = -1; private set

    /** The channel */
    abstract val channel: T

    /**
     * Set up the connection
     */
    open fun setup() {

    }

    /**
     * Set the connected state to true.
     * Should be called manually when we're connected.
     */
    open fun connect() {
        this.connected = true
        this.connectTime = System.currentTimeMillis()
    }

    /**
     * Set the connected state to false.
     * Should be called manually when we're disconnected.
     */
    open fun disconnect() {
        this.connected = false
        this.connectTime = -1L
    }
}