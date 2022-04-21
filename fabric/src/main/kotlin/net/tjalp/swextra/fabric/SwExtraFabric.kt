package net.tjalp.swextra.fabric

import net.fabricmc.api.ClientModInitializer
import net.tjalp.swextra.core.SwExtra
import net.tjalp.swextra.core.platform.Platform
import net.tjalp.swextra.fabric.platform.PlatformImpl

/**
 * The Fabric mod initializer for SwExtra
 */
object SwExtraFabric: ClientModInitializer, SwExtra() {

    override fun onInitializeClient() {
        init()
        postInit()
    }

    override fun postInit() {
        super.postInit()
    }

    override fun initPlatform(): Platform = PlatformImpl()
}