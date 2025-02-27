package com.fofr.item;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FracturedBlock extends Block {
    public FracturedBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("item.fractured-mod.general.tooltip_1").formatted(Formatting.BLUE).formatted(Formatting.ITALIC));
        super.appendTooltip(stack, world, tooltip, options);
    }
}
