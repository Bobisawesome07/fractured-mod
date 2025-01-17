package com.fofr;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(FracturedMod.MOD_ID, name);

        // Register the block.
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings().maxCount(64));
            Registry.register(Registries.ITEM, id, blockItem);
        }

        // Return the registered block!
        return Registry.register(Registries.BLOCK, id, block);
    }
    public static final Block FAKE_BEDROCK = register(
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "fake_bedrock",
            true
            );
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register((itemGroup) -> itemGroup.add(FAKE_BEDROCK));
    }


}
