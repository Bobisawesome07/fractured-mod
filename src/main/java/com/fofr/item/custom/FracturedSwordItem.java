package com.fofr.item.custom;

import com.fofr.item.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.HashMap;

public class FracturedSwordItem extends Item {
    
    private void resetCooldown(PlayerEntity user, Item sword){
        user.getItemCooldownManager().set(sword, 300);
    }
    public FracturedSwordItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()&&!user.getItemCooldownManager().isCoolingDown(this.asItem())) {

            user.sendMessage(Text.literal("You opened a fractured portal!"));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.fractured_sword.tooltip").formatted(Formatting.GOLD).formatted(Formatting.ITALIC).formatted(Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }
}