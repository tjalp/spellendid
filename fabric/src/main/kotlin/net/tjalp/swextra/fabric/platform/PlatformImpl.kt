package net.tjalp.swextra.fabric.platform

import net.fabricmc.loader.impl.FabricLoaderImpl
import net.tjalp.swextra.core.platform.Platform
import net.tjalp.swextra.fabric.SwExtraFabric

class PlatformImpl : Platform {

    override val name: String = SwExtraFabric.METADATA.name
    override val platformName: String = "Fabric"
    override val isDevelopmentEnvironment: Boolean = FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment
    override val displayVersion: String
        get() = version + (if (isDevelopmentEnvironment) "-dev" else "")
    override val version: String = SwExtraFabric.METADATA.version.friendlyString

}