package io.github.bobisawesome07.item

import io.github.bobisawesome07.FracturedMod
import io.github.bobisawesome07.item.custom.FracturedSwordItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModItems {
    /**
     * Tools and Weapons
     */
    @JvmField
    val FRACTURED_SWORD: Item = FracturedSwordItem(
        ToolMaterials.NETHERITE,  // uses netherite stats
        8,  // attack damage modifier, adjust as needed
        -2.4f,  // attack speed modifier, adjust as needed
        FabricItemSettings().maxCount(1).fireproof()
    )

    /**
     * Materials and Resources
     */
    @JvmField
    val FRACTURED_SHARD: Item = registerItem(
        "fractured_shard",
        FracturedItem(FabricItemSettings())
    )
    @JvmField
    val FRACTURED_UPGRADE_TEMPLATE: Item = registerItem(
        "fractured_upgrade_template",
        FracturedItem(FabricItemSettings())
    )

    /**
     * Creative Tab Registration Methods
     */
    private fun addToIngredientsTab(entries: FabricItemGroupEntries) {
        entries.add(FRACTURED_SHARD)
    }

    /**
     * Registers an item with the game registry
     */
    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(FracturedMod.MOD_ID, name), item)
    }

    /**
     * Called during mod initialization to register all items and add them to appropriate tabs
     */
    fun registerItems() {
        FracturedMod.LOGGER.info("Registering Mod Items for " + FracturedMod.MOD_ID)


        // Register items to creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(ModifyEntries { obj: FabricItemGroupEntries? -> addToIngredientsTab() })
    }
}
