package io.github.bobisawesome07.block.entity

import io.github.bobisawesome07.block.ModBlockEntities
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class PocketPortalBlockEntity(pos: BlockPos, state: BlockState?) :
    BlockEntity(ModBlockEntities.POCKET_PORTAL, pos, state) {
    /** Default portal duration in ticks (10 seconds)  */
    private var duration = 200

    /**
     * Gets the UUID of the player who created this portal
     */
    /**
     * Sets the owner of this portal
     */
    /** UUID of the player who created this portal  */
    @JvmField
    var playerUuid: UUID? = UUID.fromString("0983afc7-7d95-48bb-9c46-e7f4dc5b95cc")

    /** Entry location of the player who created this portal */
    @JvmField
    var entryLocation: BlockPos? = null

    /**
     * Serializes the portal block entity's state to the provided NBT compound.
     *
     * Stores the portal's remaining duration, the creator's UUID (if present), and the entry location (if present).
     *
     * @param tag The NBT compound to write the entity's data into.
     */
    public override fun writeNbt(tag: NbtCompound) {
        super.writeNbt(tag)
        tag.putInt("Duration", duration)

        if (playerUuid != null) {
            tag.putString("PlayerUUID", playerUuid.toString())
        }

        if (entryLocation != null) {
            tag.putLong("EntryLocation", entryLocation!!.asLong())
        }
    }

    /**
     * Deserializes the portal block entity's state from the given NBT compound.
     *
     * Restores the portal's duration, the UUID of the player who created it, and the entry location if present.
     *
     * @param tag The NBT compound containing the serialized block entity data.
     */
    override fun readNbt(tag: NbtCompound) {
        super.readNbt(tag)
        duration = tag.getInt("Duration")

        if (tag.contains("PlayerUUID")) {
            playerUuid = UUID.fromString(tag.getString("PlayerUUID"))
        }

        if (tag.contains("EntryLocation")) {
            entryLocation = BlockPos.fromLong(tag.getLong("EntryLocation"))
        }
    }

    companion object {
        /**
         * Handles the portal's time-based behavior.
         * The portal will disappear after its duration expires.
         */
        @JvmStatic
        fun tick(world: World, pos: BlockPos?, state: BlockState?, blockEntity: PocketPortalBlockEntity) {
            if (!world.isClient) {
                blockEntity.duration--

                if (blockEntity.duration <= 0) {
                    world.removeBlock(pos, false)
                }
            }
        }
    }
}
