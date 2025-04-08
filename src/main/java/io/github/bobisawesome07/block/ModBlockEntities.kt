package io.github.bobisawesome07.block

import io.github.bobisawesome07.FracturedMod
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos

object ModBlockEntities {
    /**
     * Block Entity Types
     */
    @JvmField
    val POCKET_PORTAL: BlockEntityType<PocketPortalBlockEntity> =
        registerBlockEntity<BlockEntityType<PocketPortalBlockEntity>>(
            "pocket_portal",
            FabricBlockEntityTypeBuilder.create<PocketPortalBlockEntity>({ pos: BlockPos?, state: BlockState? ->
                if (pos != null) {
                    PocketPortalBlockEntity(pos, state)
                }
                else{
                    null
                }
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
