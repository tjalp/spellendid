package net.tjalp.swextra.fabric.platform

import net.fabricmc.loader.impl.FabricLoaderImpl
import net.tjalp.swextra.core.platform.Platform

class PlatformImpl : Platform {

    override val platformName: String = "Fabric"
    override val isDevelopmentEnvironment: Boolean = FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment

}