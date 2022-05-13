package net.tjalp.spellendid.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.loader.api.metadata.ModMetadata
import net.fabricmc.loader.impl.FabricLoaderImpl
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil.Type.KEYSYM
import net.tjalp.spellendid.core.Spellendid
import net.tjalp.spellendid.core.networking.NetworkHandler
import net.tjalp.spellendid.core.platform.Platform
import net.tjalp.spellendid.fabric.networking.FabricNetworkHandler
import net.tjalp.spellendid.fabric.platform.PlatformImpl
import org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN
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

    override fun initKeybindings() {
        val binds = mutableListOf<Pair<KeyBinding, Int>>()

        repeat(5) {
            val keybinding = KeyBindingHelper.registerKeyBinding(
                KeyBinding(
                    "key.spellendid.spell.${it + 1}",
                    KEYSYM,
                    GLFW_KEY_UNKNOWN, // Setting the keybindings to an unknown key so it can be an optional feature
                    "title.spellendid"
                )
            )

            binds.add(Pair(keybinding, it))
        }

        // Actually make the keybindings do something
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->

            if (!networkHandler.connected) return@EndTick

            val inventory = client.player?.inventory

            binds.forEach { (bind, index) ->
                while (bind.wasPressed()) {
                    inventory?.selectedSlot = index
                }
            }
        })
    }
}