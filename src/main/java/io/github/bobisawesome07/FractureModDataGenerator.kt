package io.github.bobisawesome07

import io.github.bobisawesome07.datagen.ModWorldGenerator
import io.github.bobisawesome07.world.dimension.ModDimensions
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.world.dimension.DimensionType
import java.util.concurrent.CompletableFuture

class FractureModDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider { output: FabricDataOutput?, registriesFuture: CompletableFuture<WrapperLookup?>? ->
            ModWorldGenerator(
                output,
                registriesFuture
            )
        }
    }

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(
            RegistryKeys.DIMENSION_TYPE
        ) { context: Registerable<DimensionType?>? -> ModDimensions.bootstrapType(context) }
    }
}