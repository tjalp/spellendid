package net.tjalp.spellendid.fabric.platform

import net.fabricmc.loader.impl.FabricLoaderImpl
import net.tjalp.spellendid.core.platform.Platform
import net.tjalp.spellendid.fabric.SpellendidFabric

class PlatformImpl : Platform {

    override val name: String = SpellendidFabric.METADATA.name
    override val platformName: String = "Fabric"
    override val isDevelopmentEnvironment: Boolean = FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment
    override val displayVersion: String
        get() = version + (if (isDevelopmentEnvironment) "-dev" else "")
    override val version: String = SpellendidFabric.METADATA.version.friendlyString

}