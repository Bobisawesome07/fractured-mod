package com.fofr.block.entity;

import com.fofr.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class PocketPortalBlockEntity extends BlockEntity {

    // Duration is the number of ticks the block will exist for
    private int duration = 200; // 200 ticks = 10 seconds
    // Player UUID is the UUID of the player who created the portal
    private UUID playerUuid = UUID.fromString("0983afc7-7d95-48bb-9c46-e7f4dc5b95cc");

    // Constructor
    public PocketPortalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POCKET_PORTAL, pos, state);
    }
    // Tick method
    public static void tick(World world, BlockPos pos, BlockState state, PocketPortalBlockEntity blockEntity) {
        if (!world.isClient) {
            blockEntity.duration--;

            if (blockEntity.duration <= 0) {
                world.removeBlock(pos, false); // Removes the block when duration expires
            }
        }
    }
    // Set the duration of the block
    public void setPlayerUuid(UUID playerUuid){
        this.playerUuid = playerUuid;
    }
    // Get the player UUID
    public UUID getPlayerUuid(){
        return playerUuid;
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("Duration", duration);
        if (playerUuid!=null) {
            tag.putString("PlayerUUID", playerUuid.toString());
        }
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        duration = tag.getInt("Duration");
        if (tag.contains("PlayerUUID")){
            playerUuid = UUID.fromString(tag.getString("PlayerUUID"));
        }
    }
}
