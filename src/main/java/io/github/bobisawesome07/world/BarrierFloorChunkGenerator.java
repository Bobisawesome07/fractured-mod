package io.github.bobisawesome07.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

public class BarrierFloorChunkGenerator extends ChunkGenerator {
    private static final int FLOOR_Y = 0;
    
        // In this example, we force the dimension to use "the_end" as the single biome.
        public BarrierFloorChunkGenerator(RegistryEntry<Biome> biome) {
            super(new FixedBiomeSource(biome));
        }
        
        
        @Override
        public void populateEntities(ChunkRegion region) {
            // no-op for a void dimension
        }
        
        @Override
        public void carve(
            ChunkRegion region,
            long seed,
            NoiseConfig noiseConfig,
            BiomeAccess biomeAccess,
            StructureAccessor structureAccessor,
            Chunk chunk,
            GenerationStep.Carver carverStep
        ) {
            // Do nothing - keep it void
        }
        
        @Override
        public void buildSurface(
            ChunkRegion region,
            StructureAccessor structures,
            NoiseConfig noiseConfig,
            Chunk chunk
        ) {
            // Do nothing - we will place the barrier in generateFeatures
        }
        
        @Override
        public void generateFeatures(
            StructureWorldAccess world,
            Chunk chunk,
            StructureAccessor structureAccessor
        ) {
            // This is where we actually place our barrier floor in the chunk.
            super.generateFeatures(world, chunk, structureAccessor);
        
            ChunkPos chunkPos = chunk.getPos();
        
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    BlockPos pos = new BlockPos(chunkPos.getStartX() + x, FLOOR_Y, chunkPos.getStartZ() + z);
                world.setBlockState(pos, Blocks.BARRIER.getDefaultState(), 3);
            }
        }
    }
    
    @Override
    public int getWorldHeight() {
        return 384; // or whatever you like
    }
    
    @Override
    public int getSeaLevel() {
        return 0; // not relevant for “void,” but you must return something
    }
    
    @Nullable
    public BlockPos locateStructure(
        ServerWorld world,
        String structureName,
        BlockPos center,
        int radius,
        boolean skipExistingChunks
    ) {
        return null;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCodec'");
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig,
            StructureAccessor structureAccessor, Chunk chunk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'populateNoise'");
    }

    @Override
    public int getMinimumY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMinimumY'");
    }

    @Override
    public int getHeight(int x, int z, Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumnSample'");
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDebugHudText'");
    }
}
    
    // ... any other overrides you might need