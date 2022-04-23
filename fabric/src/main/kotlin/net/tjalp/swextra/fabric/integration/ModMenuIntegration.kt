package net.tjalp.swextra.fabric.integration

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.TranslatableText
import net.tjalp.swextra.core.SwConfig
import net.tjalp.swextra.fabric.SwExtraFabric

/**
 * The ModMenu integration
 * This will add a button to the ModMenu mod to open the config screen
 */
class ModMenuIntegration : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent: Screen? ->
            val builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(TranslatableText("title.swextra.config"))

            builder.getOrCreateCategory(TranslatableText("category.swextra.general")).apply {
                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.enable_rich_presence"), SwExtraFabric.config.enableRichPresenceFeature)
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        SwExtraFabric.config.enableRichPresenceFeature = it
                    }
                    .build())
            }

            builder.setSavingRunnable {
                SwConfig.save(SwExtraFabric.configPath, SwExtraFabric.config)
            }

            return@ConfigScreenFactory builder.build()
        }
    }
}