package com.fofr;

package com.example.yourmodid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DimensionManager {

    // ... (other fields)

    public static RegistryKey<World> getOrCreatePlayerDimension(ServerPlayerEntity player, MinecraftServer server) {
        UUID playerUUID = player.getUuid();

        if (playerDimensions.containsKey(playerUUID)) {
            return playerDimensions.get(playerUUID);
        }

        RegistryKey<World> playerDimensionKey = RegistryKey.of(Registries.WORLD, new Identifier(YourMod.MOD_ID, "player_" + playerUUID));
        playerDimensions.put(playerUUID, playerDimensionKey);

        // Create the dimension if it doesn't exist
        if (server.getWorld(playerDimensionKey) == null) {
            DimensionType dimensionType = DimensionLoader.POCKET_DIMENSION_TYPE;
            if (dimensionType == null) {
                System.out.println("Dimension type not loaded yet");
                return null;
            }

            // Get the overworld's chunk generator as a base
            ChunkGenerator overworldGenerator = server.getWorld(World.OVERWORLD).getChunkManager().getChunkGenerator();

            // Create the dimension options
            DimensionOptions dimensionOptions = new DimensionOptions(dimensionType, overworldGenerator);

            // Register the dimension
            server.getWorldRegistryKeys().add(playerDimensionKey);
            server.getRegistryManager().get(Registries.DIMENSION).add(playerDimensionKey, dimensionOptions);
            server.createWorlds(server.registryAccess(), server.getOverworld().getGenerator(), List.of(new Pair<>(playerDimensionKey, dimensionOptions)));

            System.out.println("Created dimension for player " + player.getName().getString());
        }

        return playerDimensionKey;
    }

    // ... (rest of the class)




    public static void savePlayerDimensions() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Files.createDirectories(DIMENSIONS_FILE.getParent());
            gson.toJson(playerDimensions, new FileWriter(DIMENSIONS_FILE.toFile()));
            System.out.println("Saved player dimensions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPlayerDimensions() {
        Gson gson = new GsonBuilder().create();
        try {
            if (Files.exists(DIMENSIONS_FILE)) {
                Type type = new TypeToken<HashMap<UUID, RegistryKey<World>>>() {
                }.getType();
                Map<UUID, RegistryKey<World>> loadedDimensions = gson.fromJson(new FileReader(DIMENSIONS_FILE.toFile()), type);
                playerDimensions.putAll(loadedDimensions);
                System.out.println("Loaded player dimensions");
            } else {
                System.out.println("Player Dimensions file not found, creating a new one");
                Files.createDirectories(DIMENSIONS_FILE.getParent());
                Files.createFile(DIMENSIONS_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerEvents(){
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (player != null) {
                RegistryKey<World> playerDimensionKey = DimensionManager.getOrCreatePlayerDimension(player, server);
                if(playerDimensionKey != null){
                    World playerWorld = server.getWorld(playerDimensionKey);
                    if(playerWorld != null){
                        player.teleport(playerWorld, player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                    } else {
                        System.out.println("Player world is null");
                    }
                } else {
                    System.out.println("Player dimension key is null");
                }
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if(player != null){
                playerDimensions.remove(player.getUuid());
                System.out.println("Removed player from dimension map");
            }
        });
    }

    public static void register(){
        registerEvents();
    }
}