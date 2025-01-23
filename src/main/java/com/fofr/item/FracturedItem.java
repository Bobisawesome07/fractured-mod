package com.fofr.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FracturedItem extends Item {
    public FracturedItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.general.tooltip_1").formatted(Formatting.BLUE).formatted(Formatting.ITALIC));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
