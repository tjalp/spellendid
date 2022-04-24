package net.tjalp.spellendid.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.metadata.ModMetadata
import net.fabricmc.loader.impl.FabricLoaderImpl
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.platform.Platform
import net.tjalp.spellendid.fabric.networking.FabricNetworkHandler
import net.tjalp.spellendid.fabric.platform.PlatformImpl
import java.nio.file.Path

/**
 * The Fabric mod initializer for Spellendid
 */
object SpellendidFabric : ClientModInitializer, Spellendid() {
    val METADATA: ModMetadata = FabricLoaderImpl.INSTANCE.getModContainer(MOD_ID).map { it.metadata }.orElseThrow()

    override var configPath: Path = FabricLoaderImpl.INSTANCE.configDir.resolve("spellendid.json")

    override var networkHandler: NetworkHandler<*> = FabricNetworkHandler().apply { this.setup() }

    override fun onInitializeClient() {
        init()
    }

    override fun initPlatform(): Platform = PlatformImpl()
}