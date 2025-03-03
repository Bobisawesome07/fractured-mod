package io.github.bobisawesome07.world.dimension;

import io.github.bobisawesome07.FracturedMod;
import io.github.bobisawesome07.world.BarrierFloorChunkGenerator;
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

import java.util.Map;
import java.util.OptionalLong;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.bobisawesome07.FracturedMod.MOD_ID;
import static io.github.bobisawesome07.FracturedMod.server;

public class ModDimensions {
    // Registry key for the pocket dimension type
    public static final RegistryKey<DimensionType> POCKET_DIM_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            new Identifier(MOD_ID, "pocket_dimension")
    );

    // Fantasy instance for managing runtime worlds
    private static final Fantasy fantasy = Fantasy.get(FracturedMod.getServer());

    // Cache to hold created pocket dimensions
    private static final Map<String, RuntimeWorldHandle> worldCache = new ConcurrentHashMap<>();

    // Chunk generator for pocket dimensions with a barrier floor
    private static final BarrierFloorChunkGenerator pocketGen = new BarrierFloorChunkGenerator(
            FracturedMod.getServer().getRegistryManager()
                    .get(RegistryKeys.BIOME)
                    .entryOf(BiomeKeys.THE_END)
    );

    // Configuration for pocket dimensions
    private static final RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
            .setDimensionType(POCKET_DIM_TYPE)
            .setDifficulty(Difficulty.HARD)
            .setGameRule(GameRules.DO_DAYLIGHT_CYCLE, false)
            .setGenerator(pocketGen)
            .setSeed(1234L);

    /**
     * Teleports a player to their pocket dimension
     * <p>
     * * @param world Source world
     * * @param user Player to teleport
     */
    public static void tpToPocket(World world, PlayerEntity user) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
        ServerWorld targetWorld = server.getWorld(RegistryKey.of(
                RegistryKeys.WORLD,
                new Identifier(MOD_ID, "pocket_dimension" + user.getUuidAsString())
        ));

        if (targetWorld != null) {
            serverPlayer.teleport(targetWorld, 0.0, 2.0, 0.0, 0f, 0f);
        }
    }

    /**
     * Creates a new pocket dimension or loads an existing one
     * <p>
     * * @param nameSpace Namespace for the dimension identifier
     * * @param uuid Player UUID to create a unique dimension ID
     * * @return Handle to the runtime world
     */
    public static RuntimeWorldHandle createOrLoadPocketDimension(String nameSpace, String uuid) {
        String worldId = nameSpace + ":pocket_dimension" + uuid;
        return worldCache.computeIfAbsent(worldId, id ->
                fantasy.getOrOpenPersistentWorld(
                        new Identifier(nameSpace, "pocket_dimension" + uuid),
                        worldConfig
                )
        );
    }

    /**
     * Registers the pocket dimension type during game bootstrap
     * <p>
     * * @param context Registry context
     */
    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(POCKET_DIM_TYPE, new DimensionType(
                OptionalLong.of(0),  // Fixed time
                false,              // Skylight
                false,              // Ceiling
                false,              // Ultra warm
                false,              // Natural
                1.0,                // Coordinate scale
                false,              // Bed works
                false,              // Respawn anchor works
                0,                  // Min Y
                256,                // Height
                256,                // Logical height
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.THE_END_ID,
                1.0f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
        ));
    }
}