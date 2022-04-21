package net.tjalp.swextra.core

import net.tjalp.swextra.core.platform.Platform

/**
 * The main class of the SwExtra mod
 */
abstract class SwExtra {

    lateinit var platform: Platform; private set

    /**
     * Initialize the SwExtra core
     */
    fun init() {
        this.platform = initPlatform()

        println("The current platform name is ${this.platform.platformName}")
    }

    /**
     * This method is for some misc stuff that has
     * to be done after the core has been initialized
     */
    open fun postInit() {

    }

    /**
     * Initialize the current platform
     */
    abstract fun initPlatform(): Platform
}