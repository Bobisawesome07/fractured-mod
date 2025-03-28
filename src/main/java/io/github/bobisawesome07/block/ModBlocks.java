package io.github.bobisawesome07.block;

import io.github.bobisawesome07.FracturedMod;
import io.github.bobisawesome07.block.custom.PocketPortalBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    /**
     * Standard Blocks
     */
    public static final Block FAKE_BEDROCK = registerBlock("fake_bedrock",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    /**
     * Special Blocks
     */
    public static final Block POCKET_PORTAL = registerBlock("pocket_portal",
            new PocketPortalBlock(FabricBlockSettings.create()
                    .pistonBehavior(PistonBehavior.BLOCK)
                    .noCollision()
                    .hardness(-1f)
                    .collidable(false)
                    .luminance(11)));

    /**
     * Registers a block and its corresponding item with the game registry
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(FracturedMod.MOD_ID, name), block);
    }

    /**
     * Registers a BlockItem for a block with the game registry
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM, 
                new Identifier(FracturedMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings())
        );
    }

    /**
     * Called during mod initialization to register all blocks
     */
    public static void registerBlocks() {
        FracturedMod.LOGGER.info("Registering Mod Blocks for " + FracturedMod.MOD_ID);
    }
}
