package io.github.bobisawesome07

import io.github.bobisawesome07.block.ModBlockEntities
import io.github.bobisawesome07.block.ModBlocks
import io.github.bobisawesome07.item.ModItemGroups
import io.github.bobisawesome07.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarting
import net.minecraft.server.MinecraftServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FracturedMod : ModInitializer {
    override fun onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(ServerStarting { server: MinecraftServer? ->
            Companion.server = server
        })
        ModItemGroups.registerItemGroups()
        ModItems.registerItems()
        ModBlocks.registerBlocks()
        ModBlockEntities.registerBlockEntities()
        LOGGER.info("Fracture Mod Initialized")
    }

    companion object {
        const val MOD_ID: String = "fractured-mod"
        @kotlin.jvm.JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
        @kotlin.jvm.JvmField
        var server: MinecraftServer? = null
    }
}