package net.tjalp.swextra.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.metadata.ModMetadata
import net.fabricmc.loader.impl.FabricLoaderImpl
import net.tjalp.swextra.core.SwExtra
import net.tjalp.swextra.core.platform.Platform
import net.tjalp.swextra.fabric.platform.PlatformImpl

/**
 * The Fabric mod initializer for SwExtra
 */
object SwExtraFabric : ClientModInitializer, SwExtra() {
    val METADATA: ModMetadata = FabricLoaderImpl.INSTANCE.getModContainer(MOD_ID).map { it.metadata }.orElseThrow()

    override fun onInitializeClient() {
        init()
    }

    override fun initPlatform(): Platform = PlatformImpl()
}