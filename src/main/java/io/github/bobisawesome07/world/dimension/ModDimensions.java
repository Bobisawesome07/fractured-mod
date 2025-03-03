package io.github.bobisawesome07.world.dimension;

import io.github.bobisawesome07.FracturedMod;
import net.minecraft.client.report.ReporterEnvironment.Server;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Map;
import java.util.OptionalLong;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.bobisawesome07.FracturedMod.MOD_ID;
import static io.github.bobisawesome07.FracturedMod.server;

public class ModDimensions {
    // Create a new dimension type for the pocket dimension
    public static final RegistryKey<DimensionType> POCKET_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
          new Identifier(MOD_ID, "pocket_dimension"));
    private static CustomChunkGenerator pocketGen = new CustomChunkGenerator(
            FracturedMod.getServer().getRegistryManager(),
            FracturedMod.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(BiomeKeys.THE_END).orElseThrow().value(),
            ChunkGeneratorSettings.createDefaultSettings()
    );
    // Create a runtime world configuration for the pocket dimension
    static RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
            .setDimensionType(ModDimensions.POCKET_DIM_TYPE)
            .setDifficulty(Difficulty.HARD)
            .setGameRule(GameRules.DO_DAYLIGHT_CYCLE, false)
            .setGenerator(pocketGen)
            .setSeed(1234L);
    
    // Teleport the player to the pocket dimension
    public static void tpToPocket(World world, PlayerEntity user){
        ServerPlayerEntity serverPlayer= (ServerPlayerEntity) user;
        ServerWorld targetWorld = server.getWorld(RegistryKey.of(
                RegistryKeys.WORLD,
                new Identifier(
                        "fractured-mod",
                        "pocket_dimension"+user.getUuidAsString())));
        serverPlayer.teleport(targetWorld, 0.0, 2.0, 0.0, 0f, 0f);
    }
    // Cache the pocket dimension
    private static final Map<String, RuntimeWorldHandle> worldCache = new ConcurrentHashMap<>();
    // Create or load the pocket dimension
    public static RuntimeWorldHandle createOrLoadPocketDimension(String nameSpace, String uuid, MinecraftServer server){
        Fantasy fantasy = Fantasy.get(server);
        String worldId = nameSpace + ":pocket_dimension" + uuid;
        return worldCache.computeIfAbsent(worldId, id -> fantasy.getOrOpenPersistentWorld(new Identifier(nameSpace, "pocket_dimension" + uuid), worldConfig));
    }

    // Bootstrap the pocket dimension type
    public static void bootstrapType(Registerable<DimensionType> context){
        context.register(POCKET_DIM_TYPE, new DimensionType(
                OptionalLong.of(0),
                false,
                false,
                false,
                false,
                1.0,
                false,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.THE_END_ID,
                1.0f,
                new DimensionType.MonsterSettings(false,false, UniformIntProvider.create(0,0),0)));
    }
}