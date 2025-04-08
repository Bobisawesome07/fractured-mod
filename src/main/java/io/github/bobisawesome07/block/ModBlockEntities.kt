package io.github.bobisawesome07.block

import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModBlockEntities {
    /**
     * Block Entity Types
     */
    @JvmField
    val POCKET_PORTAL: BlockEntityType<PocketPortalBlockEntity>? =
        registerBlockEntity<BlockEntityType<PocketPortalBlockEntity>>(
            "pocket_portal",
            FabricBlockEntityTypeBuilder.create<PocketPortalBlockEntity>(FabricBlockEntityTypeBuilder.Factory<PocketPortalBlockEntity> { pos: BlockPos?, state: BlockState? ->
                PocketPortalBlockEntity(
                    pos,
                    state
                )
            }, ModBlocks.POCKET_PORTAL).build()
        )

    /**
     * Registers a block entity type with the game registry
     */
    private fun <T : BlockEntityType<*>?> registerBlockEntity(name: String, blockEntityType: T): T {
        return Registry.register<BlockEntityType<*>, T>(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier(FracturedMod.MOD_ID, name),
            blockEntityType
        )
    }

    /**
     * Called during mod initialization to register all block entities
     */
    fun registerBlockEntities() {
        FracturedMod.LOGGER.info("Registering Block Entities for " + FracturedMod.MOD_ID)
    }
}
