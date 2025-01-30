package com.fofr.block.custom;

import com.fofr.block.entity.PocketPortalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof PocketPortalBlockEntity portal) {
                PocketPortalBlockEntity.tick(world1, pos, state1, portal);
            }
        };
    }
}
