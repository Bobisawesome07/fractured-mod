package com.fofr;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

import java.util.Map;
import com.google.common.collect.ImmutableMap;

public class PocketDimensionGenerator {
    public static void createPocketDimension(MinecraftServer server, RegistryKey<net.minecraft.world.World> dimensionKey) {

        // Get the world's level resource
        LevelResource levelResource = server.getWorldLoader().getLevelStorage().getLevelResource(server.getSaveProperties().getLevelName());

        // Get the WorldPreset from registry
        WorldPreset preset = server.getRegistryManager().get(RegistryKeys.WORLD_PRESET).get(new Identifier(MyMod.MOD_ID, "pocket_preset"));

        // Get the DimensionType from the preset
        RegistryKey<DimensionType> dimensionTypeKey = preset.dimensionType();
        LevelStem levelStem = new LevelStem(dimensionTypeKey, preset.generator());

        // Get the current world data.
        WorldData worldData = server.getWorldData();

        // Create a new world configuration with the new dimension added.
        Map<RegistryKey<World>, LevelStem> dimensions = new ImmutableMap.Builder<RegistryKey<net.minecraft.world.World>, LevelStem>()
                .putAll(worldData.worldGenSettings().dimensions())
                .put(dimensionKey, levelStem)
                .buildOrThrow();

        WorldOptions worldOptions = new WorldOptions(worldData.worldGenSettings().seed(), worldData.worldGenSettings().generateStructures(), worldData.worldGenSettings().hasBonusChest(), worldData.worldGenSettings().isDebugWorld());

        WorldData newWorldData = new LevelInfo(worldData.getLevelName(), worldData.getGameType(), worldData.isHardcore(), worldData.getDifficulty(), worldData.isDifficultyLocked(), worldData.getGameRules(), worldData.getDataPackSettings());

        // Create the new world. This is the crucial change
        server.createLevels(dimensions, worldOptions, newWorldData);

    }
}
