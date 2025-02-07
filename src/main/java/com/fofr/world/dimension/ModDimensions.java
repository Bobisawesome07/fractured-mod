package com.fofr.world.dimension;

import com.fofr.FracturedMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;

import static com.fofr.FracturedMod.MOD_ID;

import java.util.OptionalLong;

public class ModDimensions {

    public static final RegistryKey<DimensionType> POCKET_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
          new Identifier(MOD_ID, "pocket_dimension"));
    Fantasy fantasy = Fantasy.get(FracturedMod.getServer());
    RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
            .setDimensionType(ModDimensions.POCKET_DIM_TYPE)
            .setDifficulty(Difficulty.HARD)
            .setGameRule(GameRules.DO_DAYLIGHT_CYCLE, false)
            .setGenerator(FracturedMod.getServer().getOverworld().getChunkManager().getChunkGenerator())
            .setSeed(1234L);

    public static void createOrLoadPocketDimension(String nameSpace, String uuid){
        RuntimeWorldHandle worldHandle = fantasy.getOrOpenPersistentWorld(new Identifier(nameSpace, "pocket_dimension"+uuid), worldConfig);
    }

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