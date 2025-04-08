package io.github.bobisawesome07.world.dimension

import io.github.bobisawesome07.FracturedMod
import io.github.bobisawesome07.world.BarrierFloorChunkGenerator
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.world.Difficulty
import net.minecraft.world.GameRules
import net.minecraft.world.World
import net.minecraft.world.biome.BiomeKeys
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.dimension.DimensionType.MonsterSettings
import net.minecraft.world.dimension.DimensionTypes
import xyz.nucleoid.fantasy.Fantasy
import xyz.nucleoid.fantasy.RuntimeWorldConfig
import xyz.nucleoid.fantasy.RuntimeWorldHandle
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object ModDimensions {
    // Registry key for the pocket dimension type
    val POCKET_DIM_TYPE: RegistryKey<DimensionType> = RegistryKey.of(
        RegistryKeys.DIMENSION_TYPE,
        Identifier(FracturedMod.MOD_ID, "pocket_dimension")
    )

    // Fantasy instance for managing runtime worlds
    private val fantasy: Fantasy = Fantasy.get(FracturedMod.server)

    // Cache to hold created pocket dimensions
    private val worldCache: MutableMap<String, RuntimeWorldHandle> = ConcurrentHashMap()

    // Chunk generator for pocket dimensions with a barrier floor
    private val pocketGen = BarrierFloorChunkGenerator(
        FracturedMod.server!!.registryManager
            .get(RegistryKeys.BIOME)
            .entryOf(BiomeKeys.BEACH)
    )

    // Configuration for pocket dimensions
    private val worldConfig: RuntimeWorldConfig = RuntimeWorldConfig()
        .setDimensionType(POCKET_DIM_TYPE)
        .setDifficulty(Difficulty.HARD)
        .setGameRule(GameRules.DO_DAYLIGHT_CYCLE, false)
        .setGenerator(pocketGen)
        .setSeed(1234L)

    /**
     * Teleports a player to their pocket dimension
     *
     * @param world Source world
     * @param user Player to teleport
     */
    @JvmStatic
    fun tpToPocket(world: World?, user: PlayerEntity) {
        val serverPlayer = user as ServerPlayerEntity
        val targetWorld = FracturedMod.server!!.getWorld(
            RegistryKey.of(
                RegistryKeys.WORLD,
                Identifier(FracturedMod.MOD_ID, "pocket_dimension" + user.getUuidAsString())
            )
        )

        if (targetWorld != null) {
            serverPlayer.teleport(targetWorld, 0.0, 2.0, 0.0, 0f, 0f)
        }
    }

    /**
     * Creates a new pocket dimension or loads an existing one
     *
     * @param nameSpace Namespace for the dimension identifier
     * @param uuid Player UUID to create a unique dimension ID
     * @return Handle to the runtime world
     */
    @JvmStatic
    fun createOrLoadPocketDimension(nameSpace: String, uuid: String): RuntimeWorldHandle {
        val worldId = "$nameSpace:pocket_dimension$uuid"
        return worldCache.computeIfAbsent(worldId) { id: String? ->
            fantasy.getOrOpenPersistentWorld(
                Identifier(nameSpace, "pocket_dimension$uuid"),
                worldConfig
            )
        }
    }

    /**
     * Registers the pocket dimension type during game bootstrap
     *
     * @param context Registry context
     */
    fun bootstrapType(context: Registerable<DimensionType?>?) {
        context.register(
            POCKET_DIM_TYPE, DimensionType(
                OptionalLong.of(0),  // Fixed time
                false,  // Skylight
                false,  // Ceiling
                false,  // Ultra warm
                false,  // Natural
                1.0,  // Coordinate scale
                false,  // Bed works
                false,  // Respawn anchor works
                0,  // Min Y
                256,  // Height
                256,  // Logical height
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.OVERWORLD_ID,
                1.0f,
                MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
            )
        )
    }
}