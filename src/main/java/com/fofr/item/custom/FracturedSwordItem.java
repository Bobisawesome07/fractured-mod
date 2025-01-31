package com.fofr.item.custom;

import com.fofr.block.ModBlockEntities;
import com.fofr.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fofr.FracturedMod.MOD_ID;

public class FracturedSwordItem extends Item {
    
    private void resetCooldown(PlayerEntity user, Item sword){
        user.getItemCooldownManager().set(sword, 300);
    }
    public FracturedSwordItem(Settings settings) {
        super(settings);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private boolean isAir(World world, BlockPos blockPos){
        return(world.getBlockState(blockPos).getBlock()==Blocks.AIR||world.getBlockState(blockPos).getBlock()==Blocks.CAVE_AIR);
    }
    private void createPortal(World world, BlockPos blockPos, Direction lookedAtFace){
        HashMap<Direction, BlockPos> neighboringBlocks=new HashMap<net.minecraft.util.math.Direction, BlockPos>();
        neighboringBlocks.put(Direction.EAST,blockPos.east());
        neighboringBlocks.put(Direction.WEST,blockPos.west());
        neighboringBlocks.put(Direction.UP,blockPos.up());
        neighboringBlocks.put(Direction.DOWN,blockPos.down());
        neighboringBlocks.put(Direction.SOUTH,blockPos.south());
        neighboringBlocks.put(Direction.NORTH,blockPos.north());
        if (lookedAtFace != null && neighboringBlocks.containsKey(lookedAtFace)) {
            BlockPos targetBlock = neighboringBlocks.get(lookedAtFace);
            if (world.getBlockState(targetBlock).isAir()) {  // Or other condition
                LOGGER.debug("Setting portal block at " + targetBlock);
                world.setBlockState(targetBlock, ModBlocks.POCKET_PORTAL.getDefaultState());
            }
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()&&!user.getItemCooldownManager().isCoolingDown(this.asItem())) {
            LOGGER.debug("Fractured Sword Event Triggered");
            HitResult hitResult= user.raycast(10.0,1f,false);
            BlockHitResult blockHitResult=(BlockHitResult)hitResult;
            BlockPos blockPos = BlockPos.ofFloored(hitResult.getPos());
            if(hitResult.getType() == HitResult.Type.BLOCK){
                LOGGER.debug("Raycast successful, attempting to gen portal block");
                createPortal(world, blockPos, blockHitResult.getSide());
            }
            else{
                LOGGER.debug("Raycast missed");
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.fractured_sword.tooltip").formatted(Formatting.GOLD).formatted(Formatting.ITALIC).formatted(Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }
}