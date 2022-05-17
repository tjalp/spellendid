package net.tjalp.spellendid.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.loader.api.metadata.ModMetadata
import net.fabricmc.loader.impl.FabricLoaderImpl
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil.Type.KEYSYM
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket
import net.minecraft.util.math.BlockPos
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
        val swapKeyBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.spellendid.spell.swap",
                KEYSYM,
                GLFW_KEY_UNKNOWN, // Setting the keybindings to an unknown key so it can be an optional feature
                "title.spellendid"
            )
        )

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

            val player = client.player
            val inventory = player?.inventory

            binds.forEach { (bind, index) ->
                while (bind.wasPressed()) {
                    inventory?.selectedSlot = index
                }
            }

            while (swapKeyBinding.wasPressed()) {
                if (player != null) {
                    MinecraftClient.getInstance().networkHandler?.sendPacket(
                        PlayerActionC2SPacket(
                            PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                            player.blockPos,
                            player.movementDirection
                        )
                    )
                }
            }
        })
    }
}