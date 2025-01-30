package com.fofr.block.entity;

import com.fofr.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import static com.fofr.FracturedMod.MOD_ID;

public class ModBlockEntityTypes{
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, path), blockEntityType);
      }
    public static final BlockEntityType<PocketPortalBlockEntity> POCKET_PORTAL = register(
            "path",
            BlockEntityType.Builder.create(PocketPortalBlockEntity::new, ModBlocks.POCKET_PORTAL).build()
        );
    public static void initialize() {
    }
}