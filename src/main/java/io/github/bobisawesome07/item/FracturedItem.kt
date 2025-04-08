package io.github.bobisawesome07.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World

class FracturedItem(settings: Settings) : Item(settings) {
    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip.add(
            Text.translatable("item.fractured-mod.general.tooltip_1").formatted(Formatting.BLUE).formatted(
                Formatting.ITALIC
            )
        )
        super.appendTooltip(stack, world, tooltip, context)
    }
}
