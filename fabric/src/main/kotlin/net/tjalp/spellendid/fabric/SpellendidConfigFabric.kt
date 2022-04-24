package net.tjalp.spellendid.fabric

import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.TranslatableText
import net.tjalp.spellendid.core.SpellendidConfig

object SpellendidConfigFabric {

    fun buildConfigScreen(parent: Screen): Screen {
        val config = SpellendidFabric.config
        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(TranslatableText("title.spellendid.config"))

        builder.getOrCreateCategory(TranslatableText("category.spellendid.general")).apply {
            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.remove_loading_terrain_delay"), config.removeLoadingTerrainDelay)
                .setTooltip(TranslatableText("option.spellendid.remove_loading_terrain_delay.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.removeLoadingTerrainDelay = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.remove_loading_terrain_delay_everywhere"), config.removeLoadingTerrainDelayEverywhere)
                .setTooltip(TranslatableText("option.spellendid.remove_loading_terrain_delay_everywhere.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.removeLoadingTerrainDelayEverywhere = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.play_fall_damage_sounds"), config.playFallDamageSounds)
                .setTooltip(TranslatableText("option.spellendid.play_fall_damage_sounds.tooltip"))
                .setDefaultValue(false)
                .setSaveConsumer {
                    config.playFallDamageSounds = it
                }
                .build())
        }

        builder.getOrCreateCategory(TranslatableText("category.spellendid.discord")).apply {
            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.enable_rich_presence"), config.enableRichPresenceFeature)
                .setTooltip(TranslatableText("option.spellendid.enable_rich_presence.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.enableRichPresenceFeature = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.rich_presence_display_time"), config.richPresenceDisplayTime)
                .setTooltip(TranslatableText("option.spellendid.rich_presence_display_time.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.richPresenceDisplayTime = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(TranslatableText("option.spellendid.rich_presence_display_server"), config.richPresenceDisplayServer)
                .setTooltip(TranslatableText("option.spellendid.rich_presence_display_server.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.richPresenceDisplayServer = it
                }
                .build())
        }

        builder.setSavingRunnable {
            SpellendidConfig.save(SpellendidFabric.configPath, config)
        }

        return builder.build()
    }
}