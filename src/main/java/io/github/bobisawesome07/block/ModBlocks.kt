package io.github.bobisawesome07.block

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModBlocks {
    /**
     * Standard Blocks
     */
    val FAKE_BEDROCK: Block? = registerBlock(
        "fake_bedrock",
        Block(FabricBlockSettings.copyOf(Blocks.STONE))
    )

    /**
     * Special Blocks
     */
    @JvmField
    val POCKET_PORTAL: Block? = registerBlock(
        "pocket_portal",
        PocketPortalBlock(
            FabricBlockSettings.create()
                .pistonBehavior(PistonBehavior.BLOCK)
                .noCollision()
                .hardness(-1f)
                .collidable(false)
                .luminance(11)
        )
    )

    /**
     * Registers a block and its corresponding item with the game registry
     */
    private fun registerBlock(name: String, block: Block): Block? {
        registerBlockItem(name, block)
        return Registry.register<Block, Block>(Registries.BLOCK, Identifier(FracturedMod.MOD_ID, name), block)
    }

    /**
     * Registers a BlockItem for a block with the game registry
     */
    private fun registerBlockItem(name: String, block: Block): Item {
        return Registry.register<Item, BlockItem>(
            Registries.ITEM,
            Identifier(FracturedMod.MOD_ID, name),
            BlockItem(block, FabricItemSettings())
        )
    }

    /**
     * Called during mod initialization to register all blocks
     */
    fun registerBlocks() {
        FracturedMod.LOGGER.info("Registering Mod Blocks for " + FracturedMod.MOD_ID)
    }
}
