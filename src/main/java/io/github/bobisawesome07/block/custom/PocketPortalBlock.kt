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
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return PocketPortalBlockEntity(pos, state)
    }

    /**
     * Handles entity collision with the portal block.
     * If the colliding entity is the player who created this portal,
     * they will be teleported to their pocket dimension.
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
