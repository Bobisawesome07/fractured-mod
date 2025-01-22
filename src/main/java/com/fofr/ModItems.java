package com.fofr;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.fofr.FracturedMod.MOD_ID;

public class ModItems {
    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of(MOD_ID, id);

        // Return the registered item!
        return Registry.register(Registries.ITEM, itemID, item);
    }
    public static final Item FRACTURED_SWORD = register(
            new SwordItem(FracturedMaterial.INSTANCE, -4, -2.6f, new FabricItemSettings()),
            "fractured_sword"
    );
    public static final Item FRACTURED_SHARD = register(
            new Item(new Item.Settings().maxCount(64)),
            "fractured_shard"
    );


    // 1) Create a registry key for your new Item Group
    public static final RegistryKey<ItemGroup> FRACTURED_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(MOD_ID, "fractured_items"));

    // 2) Build the ItemGroup instance itself
    public static final ItemGroup FRACTURED_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.FRACTURED_SWORD))   // the icon shown on the tab
            // you can use Text.literal() if you want raw text, or a translation key if you prefer
            .displayName(Text.literal("Fractured Items"))
            .build();


    public static void initialize() {
        // 3) Register your custom Item Group with the game
        Registry.register(Registries.ITEM_GROUP, FRACTURED_ITEM_GROUP_KEY, FRACTURED_ITEM_GROUP);

        // 4) Add items into the tab AFTER it’s registered.
        //    This event runs at the right time to let you add items to your custom group.
        ItemGroupEvents.modifyEntriesEvent(FRACTURED_ITEM_GROUP_KEY).register(entries -> {
            entries.add(ModItems.FRACTURED_SWORD);
            entries.add(ModItems.FRACTURED_SHARD);
            entries.add(ModBlocks.FAKE_BEDROCK);
        });

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
        //        .register((itemGroup) -> itemGroup.add(ModItems.FRACTURED_SHARD));

        // (Register your items FIRST or you won't be able to add them!)
        //ModItems.registerAll();
    }



// Tool-tips for Items
    public static class ModTooltips extends Item {
        public ModTooltips(Settings settings) {
            super(settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("Testing Tooltip!").formatted(Formatting.GOLD));
        }

    }
}