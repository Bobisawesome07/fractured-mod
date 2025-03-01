package com.fofr.block;

import com.fofr.FracturedMod;
import com.fofr.block.entity.PocketPortalBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<PocketPortalBlockEntity> POCKET_PORTAL =
            Registry.register(Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(FracturedMod.MOD_ID, "pocket_portal"),
                    FabricBlockEntityTypeBuilder.create(PocketPortalBlockEntity::new, ModBlocks.POCKET_PORTAL).build());

    public static void registerBlockEntities() {
        FracturedMod.LOGGER.info("Registering Block Entities for " + FracturedMod.MOD_ID);
    }
}
