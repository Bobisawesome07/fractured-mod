package com.fofr;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.dimension.DimensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FracturedMod implements ModInitializer {

	public static final String MOD_ID = "fractured-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.initialize();
		ModBlocks.initialize();
		LOGGER.info("Fracture Mod Initialized");

		UseItemCallback.EVENT.register((player, world, hand) -> {
			ItemStack stack = player.getStackInHand(hand);
			LOGGER.info(player.getWorld().asString());

			if (stack.isOf(ModItems.FRACTURED_SWORD)&&player.getHealth()>10) {
				System.out.println(player.getName().getString() + "with uuid "+ player.getUuidAsString()+" opened a fractured portal");
				return TypedActionResult.success(stack);
			}

			return TypedActionResult.pass(stack);
		});
	}
}