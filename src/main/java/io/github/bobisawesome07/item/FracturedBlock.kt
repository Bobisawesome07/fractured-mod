package io.github.bobisawesome07.item

import net.minecraft.block.Block
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.BlockView

class FracturedBlock(settings: Settings) : Block(settings) {
    override fun appendTooltip(
        stack: ItemStack,
        world: BlockView?,
        tooltip: MutableList<Text>,
        options: TooltipContext
    ) {
        tooltip.add(
            Text.translatable("item.fractured-mod.general.tooltip_1").formatted(Formatting.BLUE).formatted(
                Formatting.ITALIC
            )
        )
        super.appendTooltip(stack, world, tooltip, options)
    }
}
