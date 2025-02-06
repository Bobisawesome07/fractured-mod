package com.fofr.util;

import com.fofr.FracturedMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;

public class DimensionUtils {
    MinecraftServer server = FracturedMod.getServer();
    Fantasy fantasy = Fantasy.get(server);
    DimensionType pocket=new Dim
    RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
    .setDimensionType(DimensionTypes.OVERWORLD)

}