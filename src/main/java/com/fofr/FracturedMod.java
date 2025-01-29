package com.fofr;

import com.fofr.block.ModBlocks;
import com.fofr.entity.Troll;
import com.fofr.item.ModItemGroups;
import com.fofr.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FracturedMod implements ModInitializer {

	public static final String MOD_ID = "fractured-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<Troll> TROLL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("fratured-mod", "troll"),
            EntityType.Builder.create(Troll::new, SpawnGroup.CREATURE).setDimensions(0.75f,0.8f).build("troll:")
    );

	@Override
	public void onInitialize() {

    ModItemGroups.registerItemGroups();
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		FabricDefaultAttributeRegistry.register(TROLL, Troll.createMobAttributes());
    
		LOGGER.info("Fracture Mod Initialized");

	}
}