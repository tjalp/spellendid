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
            val config = SwExtraFabric.config
            val builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(TranslatableText("title.swextra.config"))

            builder.getOrCreateCategory(TranslatableText("category.swextra.general")).apply {
                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.remove_loading_terrain_delay"), config.removeLoadingTerrainDelay)
                    .setTooltip(TranslatableText("option.swextra.remove_loading_terrain_delay.tooltip"))
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        config.removeLoadingTerrainDelay = it
                    }
                    .build())

                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.remove_loading_terrain_delay_everywhere"), config.removeLoadingTerrainDelayEverywhere)
                    .setTooltip(TranslatableText("option.swextra.remove_loading_terrain_delay_everywhere.tooltip"))
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        config.removeLoadingTerrainDelayEverywhere = it
                    }
                    .build())

                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.play_fall_damage_sounds"), config.playFallDamageSounds)
                    .setTooltip(TranslatableText("option.swextra.play_fall_damage_sounds.tooltip"))
                    .setDefaultValue(false)
                    .setSaveConsumer {
                        config.playFallDamageSounds = it
                    }
                    .build())
            }

            builder.getOrCreateCategory(TranslatableText("category.swextra.discord")).apply {
                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.enable_rich_presence"), config.enableRichPresenceFeature)
                    .setTooltip(TranslatableText("option.swextra.enable_rich_presence.tooltip"))
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        config.enableRichPresenceFeature = it
                    }
                    .build())

                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.rich_presence_display_time"), config.richPresenceDisplayTime)
                    .setTooltip(TranslatableText("option.swextra.rich_presence_display_time.tooltip"))
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        config.richPresenceDisplayTime = it
                    }
                    .build())

                addEntry(builder.entryBuilder()
                    .startBooleanToggle(TranslatableText("option.swextra.rich_presence_display_server"), config.richPresenceDisplayServer)
                    .setTooltip(TranslatableText("option.swextra.rich_presence_display_server.tooltip"))
                    .setDefaultValue(true)
                    .setSaveConsumer {
                        config.richPresenceDisplayServer = it
                    }
                    .build())
            }

            builder.setSavingRunnable {
                SwConfig.save(SwExtraFabric.configPath, config)
            }

            return@ConfigScreenFactory builder.build()
        }
    }
}