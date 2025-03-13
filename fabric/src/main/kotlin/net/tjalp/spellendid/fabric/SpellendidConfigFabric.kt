package net.tjalp.spellendid.fabric

import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text.translatable
import net.tjalp.spellendid.core.SpellendidConfig

object SpellendidConfigFabric {

    fun buildConfigScreen(parent: Screen): Screen {
        val config = SpellendidFabric.config
        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(translatable("title.spellendid.config"))

        builder.getOrCreateCategory(translatable("category.spellendid.general")).apply {
            addEntry(builder.entryBuilder()
                .startStrField(translatable("option.spellendid.api_key"), config.apiKey)
                .setTooltip(translatable("option.spellendid.api_key.tooltip"))
                .setDefaultValue("")
                .setSaveConsumer {
                    config.apiKey = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.remove_loading_terrain_delay"), config.removeLoadingTerrainDelay)
                .setTooltip(translatable("option.spellendid.remove_loading_terrain_delay.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.removeLoadingTerrainDelay = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.remove_loading_terrain_delay_everywhere"), config.removeLoadingTerrainDelayEverywhere)
                .setTooltip(translatable("option.spellendid.remove_loading_terrain_delay_everywhere.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.removeLoadingTerrainDelayEverywhere = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.play_fall_damage_sounds"), config.playFallDamageSounds)
                .setTooltip(translatable("option.spellendid.play_fall_damage_sounds.tooltip"))
                .setDefaultValue(false)
                .setSaveConsumer {
                    config.playFallDamageSounds = it
                }
                .build())
        }

        builder.getOrCreateCategory(translatable("category.spellendid.discord")).apply {
            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.enable_rich_presence"), config.enableRichPresenceFeature)
                .setTooltip(translatable("option.spellendid.enable_rich_presence.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.enableRichPresenceFeature = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.rich_presence_display_time"), config.richPresenceDisplayTime)
                .setTooltip(translatable("option.spellendid.rich_presence_display_time.tooltip"))
                .setDefaultValue(true)
                .setSaveConsumer {
                    config.richPresenceDisplayTime = it
                }
                .build())

            addEntry(builder.entryBuilder()
                .startBooleanToggle(translatable("option.spellendid.rich_presence_display_server"), config.richPresenceDisplayServer)
                .setTooltip(translatable("option.spellendid.rich_presence_display_server.tooltip"))
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