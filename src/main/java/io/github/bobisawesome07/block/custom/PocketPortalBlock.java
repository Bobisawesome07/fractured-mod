package io.github.bobisawesome07.block.custom;

import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity;
import io.github.bobisawesome07.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

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
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        // Check if the entity is a player and if the player is the one who created the portal
        if (!world.isClient && entity instanceof PlayerEntity) {
            PocketPortalBlockEntity portalEntity = (PocketPortalBlockEntity) world.getBlockEntity(pos);
            if (portalEntity != null) {
                UUID portalUuid = portalEntity.getPlayerUuid();
                UUID entityUuid = entity.getUuid();
                // If the player who created the portal is the one who entered the portal, teleport them to the pocket dimension
                if (portalUuid.equals(entityUuid)) {
                    ServerPlayerEntity player = (ServerPlayerEntity) entity;
                    ModDimensions.tpToPocket(world, player);
                }
            }
        }
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
