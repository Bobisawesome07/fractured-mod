package com.fofr.item;

import com.fofr.FracturedMod;
import com.fofr.block.ModBlocks;
import com.fofr.item.custom.FracturedSwordItem;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // ---------- Declare a new item --------------------------------------------------------------------------------------------------- //
    public static final Item FRACTURED_SWORD = registerItem("fractured_sword", new FracturedSwordItem(new FabricItemSettings()
            .maxCount(1)
            .maxDamage(8)
    ));
    public static final Item FRACTURED_SHARD = registerItem("fractured_shard", new FracturedItem(new FabricItemSettings()));
    public static final Item FRACTURED_UPGRADE_TEMPLATE = registerItem("fractured_upgrade_template", new FracturedItem(new FabricItemSettings()));


    // ---------- Tab Methods (entries.add for and item) ------------------------------------------------------------------------------- //
    private static void IngredientTab(FabricItemGroupEntries entries){
        entries.add(ModItems.FRACTURED_SHARD);

    }






    // --------- Helper Methods ------------------------------------------------------------------------------------------------------- //
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(FracturedMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        FracturedMod.LOGGER.info("Registering Mod Items for " + FracturedMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::IngredientTab);
    }
}
