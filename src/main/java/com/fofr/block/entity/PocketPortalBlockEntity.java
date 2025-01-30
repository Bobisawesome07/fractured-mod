package com.fofr.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Tickable;
import com.fofr.block.ModBlockEntities;
import com.fofr.block.ModBlocks;

public class PocketPortalBlockEntity extends BlockEntity implements Tickable {

    private int duration = 200; // Example: lasts 10 seconds (200 ticks)

    public PocketPortalBlockEntity() {
        super(ModBlockEntities.POCKET_PORTAL);
    }

    @Override
    public void tick() {
        if (!world.isClient) {
            duration--;

            if (duration <= 0) {
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
