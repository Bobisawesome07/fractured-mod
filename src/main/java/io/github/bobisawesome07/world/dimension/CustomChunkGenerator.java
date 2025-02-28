package io.github.bobisawesome07.world.dimension;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

/**
 * Custom chunk generator to generate a barrier floor in the pocket dimension.
 */
public class CustomChunkGenerator extends NoiseChunkGenerator {
    public CustomChunkGenerator(RegistryManager registryManager, BiomeSource biomeSource, ChunkGeneratorSettings settings) {
        super(registryManager.get(RegistryKeys.NOISE_SETTINGS), biomeSource, settings);
    }

    @Override
    public void buildSurface(Chunk chunk) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int minY = this.getMinimumY();
        int maxY = minY + this.getHeight();
        int yLevel = 0; // Y coordinate for the barrier floor

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                pos.set(x, yLevel - minY, z);
                chunk.setBlockState(pos, Blocks.BARRIER.getDefaultState(), false);
            }
        }
    }

    @Override
    public void generateFeatures(ChunkRegion region, StructureAccessor accessor) {
        // No features to generate.
    }

    @Override
    public void populateEntities(ChunkRegion region) {
        // No entities to populate.
    }

    @Override
    public void generateStrongholdPositions(RegistryEntryLookup<ConfiguredStructureFeature<?, ?>> lookup) {
        // No strongholds to generate.
    }

    @Override
    public void addStructureReferences(StructureAccessor accessor, Chunk chunk, StructurePlacementData placementData) {
        // No structures to reference.
    }

    @Override
    public void scheduleStructureStarts(ServerWorld world, Chunk chunk, StructureAccessor accessor) {
        // No structures to schedule.
    }
}