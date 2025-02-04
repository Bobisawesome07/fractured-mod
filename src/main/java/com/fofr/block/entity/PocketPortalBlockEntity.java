package com.fofr.block.entity;

import com.fofr.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class PocketPortalBlockEntity extends BlockEntity {

    private int duration = 200; // 200 ticks = 10 seconds

    public PocketPortalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POCKET_PORTAL, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PocketPortalBlockEntity blockEntity) {
        if (!world.isClient) {
            blockEntity.duration--;

            if (blockEntity.duration <= 0) {
                world.removeBlock(pos, false); // Removes the block when duration expires
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("Duration", duration);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        duration = tag.getInt("Duration");
    }
}
