package com.fofr.item.custom;

import com.fofr.block.ModBlocks;
import com.fofr.world.dimension.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.fofr.FracturedMod.MOD_ID;
import static com.fofr.FracturedMod.server;

public class FracturedSwordItem extends Item {
    
    private void resetCooldown(PlayerEntity user, Item sword){
        user.getItemCooldownManager().set(sword, 3000);
    }
    public FracturedSwordItem(Settings settings) {
        super(settings);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private boolean isAir(World world, BlockPos blockPos){
        return(world.getBlockState(blockPos).getBlock()==Blocks.AIR||world.getBlockState(blockPos).getBlock()==Blocks.CAVE_AIR);
    }
    private void createPortal(World world, BlockPos blockPos, Direction lookedAtFace){
        if (lookedAtFace != null) {
            if (world.getBlockState(blockPos).isAir()) {  // Or other condition
                LOGGER.debug("Setting portal block at " + blockPos);
                world.setBlockState(blockPos, ModBlocks.POCKET_PORTAL.getDefaultState());
            }
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()&&!user.getItemCooldownManager().isCoolingDown(this.asItem())) {
            LOGGER.debug("Fractured Sword Event Triggered");

            ModDimensions.createOrLoadPocketDimension("fractured-mod", user.getUuidAsString());
            HitResult hitResult= user.raycast(10.0,1f,false);
            BlockHitResult blockHitResult=(BlockHitResult)hitResult;
            BlockPos blockPos = BlockPos.ofFloored(hitResult.getPos());
            LOGGER.debug("Raycast successful, attempting to gen portal block");
                createPortal(world, blockPos, blockHitResult.getSide());
                resetCooldown(user, this);
            }
            else{
                LOGGER.debug("Raycast missed");
            }
        return super.use(world, user, hand);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.fractured_sword.tooltip").formatted(Formatting.GOLD).formatted(Formatting.ITALIC).formatted(Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }
}