package com.fofr;

import com.fofr.block.ModBlockEntities;
import com.fofr.block.ModBlocks;
import com.fofr.item.ModItemGroups;
import com.fofr.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FracturedMod implements ModInitializer {

	public static final String MOD_ID = "fractured-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftServer server;

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register(server -> {FracturedMod.server = server;});
		ModItemGroups.registerItemGroups();
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModBlockEntities.registerBlockEntities();
		LOGGER.info("Fracture Mod Initialized");
	}
	public static MinecraftServer getServer() {
        return server;
    }
}