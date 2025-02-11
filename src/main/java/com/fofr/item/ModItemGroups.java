package com.fofr.item;

import com.fofr.FracturedMod;
import com.fofr.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FRACTURED_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(FracturedMod.MOD_ID, "fractured_items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fractured"))
                    .icon(() -> new ItemStack(ModItems.FRACTURED_SWORD)).entries((displayContext, entries) -> {
                        // Add items to a custom group here:
                        entries.add(ModItems.FRACTURED_SWORD);
                        entries.add(ModItems.FRACTURED_SHARD);
                        entries.add(ModBlocks.FAKE_BEDROCK);
                        entries.add(ModItems.FRACTURED_UPGRADE_TEMPLATE);

                    }).build());

    public static void registerItemGroups() {
        FracturedMod.LOGGER.info("Registering Mod Item Groups for " + FracturedMod.MOD_ID);
    }
}
