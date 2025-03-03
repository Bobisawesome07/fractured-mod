package io.github.bobisawesome07.world.dimension;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

/**
 * Custom chunk generator to generate a barrier floor in the pocket dimension.
 */
public class CustomChunkGenerator extends ChunkGenerator {
    private final NoiseChunkGenerator noiseChunkGenerator;

    public CustomChunkGenerator(RegistryOps<?> registryOps, BiomeSource biomeSource, ChunkGeneratorSettings settings) {
        this.noiseChunkGenerator = new NoiseChunkGenerator(registryOps.getRegistry(RegistryKeys.NOISE_SETTINGS), biomeSource, settings);
    }

    public void buildSurface(Chunk chunk) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int minY = this.getMinimumY();
        int yLevel = 0; // Y coordinate for the barrier floor

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                pos.set(x, yLevel - minY, z);
                chunk.setBlockState(pos, Blocks.BARRIER.getDefaultState(), false);
            }
        }
    }
}