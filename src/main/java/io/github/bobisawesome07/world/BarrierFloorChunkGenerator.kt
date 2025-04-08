package io.github.bobisawesome07.world

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkRegion
import net.minecraft.world.HeightLimitView
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeAccess
import net.minecraft.world.biome.source.FixedBiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.Blender
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.VerticalBlockSample
import net.minecraft.world.gen.noise.NoiseConfig
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

class BarrierFloorChunkGenerator  // In this example, we force the dimension to use "the_end" as the single biome.
    (biome: RegistryEntry<Biome?>?) : ChunkGenerator(FixedBiomeSource(biome)) {
    override fun populateEntities(region: ChunkRegion) {
        // no-op for a void dimension
    }

    override fun carve(
        region: ChunkRegion,
        seed: Long,
        noiseConfig: NoiseConfig,
        biomeAccess: BiomeAccess,
        structureAccessor: StructureAccessor,
        chunk: Chunk,
        carverStep: GenerationStep.Carver
    ) {
        // Do nothing - keep it void
    }

    override fun buildSurface(
        region: ChunkRegion,
        structures: StructureAccessor,
        noiseConfig: NoiseConfig,
        chunk: Chunk
    ) {
        // Do nothing - we will place the barrier in generateFeatures
    }

    override fun generateFeatures(
        world: StructureWorldAccess,
        chunk: Chunk,
        structureAccessor: StructureAccessor
    ) {
        // This is where we actually place our barrier floor in the chunk.
        super.generateFeatures(world, chunk, structureAccessor)

        val chunkPos = chunk.pos

        for (x in 0..15) {
            for (z in 0..15) {
                val pos = BlockPos(chunkPos.startX + x, FLOOR_Y, chunkPos.startZ + z)
                world.setBlockState(pos, Blocks.BARRIER.defaultState, 3)
            }
        }
    }

    override fun getWorldHeight(): Int {
        return 256 // or whatever you like
    }

    override fun getSeaLevel(): Int {
        return 0 // not relevant for “void,” but you must return something
    }

    fun locateStructure(
        world: ServerWorld?,
        structureName: String?,
        center: BlockPos?,
        radius: Int,
        skipExistingChunks: Boolean
    ): BlockPos? {
        return null
    }

    override fun getCodec(): Codec<out ChunkGenerator?>? {
        // TODO Auto-generated method stub
        return null
    }

    override fun populateNoise(
        executor: Executor, blender: Blender, noiseConfig: NoiseConfig,
        structureAccessor: StructureAccessor, chunk: Chunk
    ): CompletableFuture<Chunk> {
        // TODO Auto-generated method stub
        return CompletableFuture.completedFuture(chunk)
    }

    override fun getMinimumY(): Int {
        // TODO Auto-generated method stub
        return 0
    }

    override fun getHeight(
        x: Int,
        z: Int,
        heightmap: Heightmap.Type,
        world: HeightLimitView,
        noiseConfig: NoiseConfig
    ): Int {
        // TODO Auto-generated method stub
        return 0
    }

    override fun getColumnSample(
        x: Int,
        z: Int,
        world: HeightLimitView,
        noiseConfig: NoiseConfig
    ): VerticalBlockSample {
        // TODO Auto-generated method stub
        return VerticalBlockSample(0, arrayOfNulls(0))
    }

    override fun getDebugHudText(text: List<String>, noiseConfig: NoiseConfig, pos: BlockPos) {
        // TODO Auto-generated method stub
    }

    companion object {
        private const val FLOOR_Y = 0
    }
} // ... any other overrides you might need

