package net.tjalp.spellendid.fabric.integration

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.gui.screen.Screen
import net.tjalp.spellendid.fabric.SpellendidConfigFabric

/**
 * The ModMenu integration
 * This will add a button to the ModMenu mod to open the config screen
 */
class ModMenuIntegration : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent: Screen ->
            return@ConfigScreenFactory SpellendidConfigFabric.buildConfigScreen(parent)
        }
    }
}