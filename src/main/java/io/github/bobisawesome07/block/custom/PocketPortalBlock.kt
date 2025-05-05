package io.github.bobisawesome07.block.custom

import io.github.bobisawesome07.block.ModBlockEntities
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity.Companion.tick
import io.github.bobisawesome07.world.dimension.ModDimensions.tpToPocket
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class PocketPortalBlock(settings: Settings) : Block(settings), BlockEntityProvider {
    private var entryLocation: BlockPos? = null

    /**
     * Creates a new `PocketPortalBlockEntity` at the specified position and block state.
     *
     * @return The created block entity for this portal block.
     */
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return PocketPortalBlockEntity(pos, state)
    }

    /**
     * Teleports the player who created the portal to their pocket dimension upon collision with the portal block.
     *
     * Stores the current block position as the entry location. Only the player whose UUID matches the portal's creator is teleported.
     *
     * @param state The current block state.
     * @param world The world in which the collision occurs.
     * @param pos The position of the portal block.
     * @param entity The entity colliding with the portal.
     */
    @Deprecated("Deprecated in Java")
    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (world.isClient || entity !is PlayerEntity) {
            return
        }

        // Get portal entity and check player ownership
        val portalEntity = world.getBlockEntity(pos) as PocketPortalBlockEntity? ?: return

        val portalUuid = portalEntity.playerUuid
        val entityUuid = entity.getUuid()

        // Store the entry location
        entryLocation = pos

        // Teleport only if this player created the portal
        if (portalUuid == entityUuid) {
            val player = entity as ServerPlayerEntity
            tpToPocket(world, player)
        }
    }

    /**
     * Returns the ticker for handling the portal's time-based behavior
     */
    override fun <T : BlockEntity?> getTicker(
        world: World,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (world.isClient) null else
            createTickerHelper(type, ModBlockEntities.POCKET_PORTAL) { world1, pos, state1, blockEntity ->
                tick(world1, pos, state1, blockEntity)
            }
    }
    @Suppress("UNCHECKED_CAST")
    private fun <E : BlockEntity?, A : BlockEntity?> createTickerHelper(
        type: BlockEntityType<E>,
        targetType: BlockEntityType<A>?,
        ticker: BlockEntityTicker<A>
    ): BlockEntityTicker<E>? {
        return if (type === targetType) ticker as BlockEntityTicker<E> else null
    }
}
