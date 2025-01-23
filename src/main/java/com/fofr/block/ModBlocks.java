package com.fofr.block;

import com.fofr.FracturedMod;
import com.fofr.item.FracturedBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // ---------- Declare a new block -------------------------------------------------------------------------------------------------- //
    public static final Block FAKE_BEDROCK = registerBlock("fake_bedrock",
            new FracturedBlock(FabricBlockSettings.copyOf(Blocks.STONE)));






    // --------- Helper Methods ------------------------------------------------------------------------------------------------------- //
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(FracturedMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(FracturedMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlocks() {
        FracturedMod.LOGGER.info("Registering Mod Blocks for " + FracturedMod.MOD_ID);
    }
}
