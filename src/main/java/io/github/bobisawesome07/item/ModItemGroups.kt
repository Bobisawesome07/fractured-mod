package io.github.bobisawesome07.item

import io.github.bobisawesome07.FracturedMod
import io.github.bobisawesome07.block.ModBlocks
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ModItemGroups {
    val FRACTURED_GROUP: ItemGroup? = Registry.register(
        Registries.ITEM_GROUP,
        Identifier(FracturedMod.MOD_ID, "fractured_items"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fractured"))
            .icon { ItemStack(ModItems.FRACTURED_SWORD) }
            .entries { displayContext: ItemGroup.DisplayContext?, entries: ItemGroup.Entries ->
                // Add items to a custom group here:
                entries.add(ModItems.FRACTURED_SWORD)
                entries.add(ModItems.FRACTURED_SHARD)
                entries.add(ModBlocks.FAKE_BEDROCK)
                entries.add(ModItems.FRACTURED_UPGRADE_TEMPLATE)
            }.build()
    )

    fun registerItemGroups() {
        FracturedMod.LOGGER.info("Registering Mod Item Groups for " + FracturedMod.MOD_ID)
    }
}
