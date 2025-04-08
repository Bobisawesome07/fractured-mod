package io.github.bobisawesome07.block;

import io.github.bobisawesome07.FracturedMod;
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    /**
     * Block Entity Types
     */
    public static final BlockEntityType<PocketPortalBlockEntity> POCKET_PORTAL = registerBlockEntity(
            "pocket_portal",
            FabricBlockEntityTypeBuilder.create(PocketPortalBlockEntity::new, ModBlocks.POCKET_PORTAL).build()
    );

    /**
     * Registers a block entity type with the game registry
     */
    private static <T extends BlockEntityType<?>> T registerBlockEntity(String name, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FracturedMod.MOD_ID, name), blockEntityType);
    }

    /**
     * Called during mod initialization to register all block entities
     */
    public static void registerBlockEntities() {
        FracturedMod.LOGGER.info("Registering Block Entities for " + FracturedMod.MOD_ID);
    }
}
