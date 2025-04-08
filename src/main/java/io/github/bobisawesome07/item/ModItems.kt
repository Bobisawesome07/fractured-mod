package io.github.bobisawesome07.item;

import io.github.bobisawesome07.FracturedMod;
import io.github.bobisawesome07.item.custom.FracturedSwordItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.ToolMaterials;

public class ModItems {
    /**
     * Tools and Weapons
     */
    public static final Item FRACTURED_SWORD = new FracturedSwordItem(
            ToolMaterials.NETHERITE, // uses netherite stats
            8, // attack damage modifier, adjust as needed
            -2.4f, // attack speed modifier, adjust as needed
            new FabricItemSettings().maxCount(1).fireproof());
    
    /**
     * Materials and Resources
     */
    public static final Item FRACTURED_SHARD = registerItem("fractured_shard", 
            new FracturedItem(new FabricItemSettings()));
    public static final Item FRACTURED_UPGRADE_TEMPLATE = registerItem("fractured_upgrade_template", 
            new FracturedItem(new FabricItemSettings()));

    /**
     * Creative Tab Registration Methods
     */
    private static void addToIngredientsTab(FabricItemGroupEntries entries) {
        entries.add(FRACTURED_SHARD);
    }

    /**
     * Registers an item with the game registry
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FracturedMod.MOD_ID, name), item);
    }

    /**
     * Called during mod initialization to register all items and add them to appropriate tabs
     */
    public static void registerItems() {
        FracturedMod.LOGGER.info("Registering Mod Items for " + FracturedMod.MOD_ID);
        
        // Register items to creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToIngredientsTab);
    }
}
