package com.fofr.util;

import com.fofr.FracturedMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;

public class DimensionUtils {
    public static RegistryKey<World> getPlayerDimensionKey(ServerPlayerEntity player) {
        return RegistryKey.of(RegistryKey.ofRegistry(new Identifier("dimension")), new Identifier(FracturedMod.MOD_ID, player.getUuidAsString()));
    }

    public static void createPlayerDimension(MinecraftServer server, ServerPlayerEntity player) {
        RegistryKey<World> playerDimensionKey = getPlayerDimensionKey(player);
        if (server.getWorld(playerDimensionKey) == null) {
            Registry<DimensionOptions> dimensionOptionsRegistry = server.getRegistryManager().get(Registry.DIMENSION_OPTIONS_KEY);
            DimensionOptions dimensionOptions = new DimensionOptions(() -> DimensionType.OVERWORLD, GeneratorOptions.createDefaultOverworldGenerator(server.getRegistryManager()));
            dimensionOptionsRegistry.add(playerDimensionKey, dimensionOptions);
        }
    }

    public static void teleportPlayerToDimension(ServerPlayerEntity player, RegistryKey<World> dimensionKey) {
        player.teleport(player.getServer().getWorld(dimensionKey), player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
    }
}
