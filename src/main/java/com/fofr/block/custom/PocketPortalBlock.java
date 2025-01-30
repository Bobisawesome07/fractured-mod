package com.fofr.block.custom;

import com.fofr.block.entity.PocketPortalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PocketPortalBlock extends Block implements BlockEntityProvider {

    public PocketPortalBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PocketPortalBlockEntity(pos, state);
    }

    @Override
    public void scheduledTick(BlockState state, World world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PocketPortalBlockEntity portal) {
            portal.tick();
        }
    }
}
