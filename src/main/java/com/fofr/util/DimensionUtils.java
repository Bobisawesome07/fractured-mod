package com.fofr.util;

import com.fofr.FracturedMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class DimensionUtils {
    public static RegistryKey<World> getPlayerDimensionKey(ServerPlayerEntity player) {
        return RegistryKey.of(RegistryKey.ofRegistry(new Identifier("dimension")), new Identifier(FracturedMod.MOD_ID, player.getUuidAsString()));
    }

    public static void createPlayerDimension(MinecraftServer server, ServerPlayerEntity player) {
        RegistryKey<World> playerDimensionKey = getPlayerDimensionKey(player);
        if (server.getWorld(playerDimensionKey)==null) {
            server.getWorlds().add(playerDimensionKey, new World(server, server.getWorkerExecutor(), server.getSaveProperties(), playerDimensionKey, DimensionTypes.OVERWORLD, server.getProfiler(), true, false, 0));
        }
    }

    public static void teleportPlayerToDimension(ServerPlayerEntity player, RegistryKey<World> dimensionKey) {
        player.teleport(player.getServer().getWorld(dimensionKey), player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
    }
}
