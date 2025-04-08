package io.github.bobisawesome07.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import java.util.concurrent.CompletableFuture

class ModWorldGenerator(output: FabricDataOutput?, registriesFuture: CompletableFuture<WrapperLookup?>?) :
    FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: WrapperLookup, entries: Entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DIMENSION_TYPE))
    }

    override fun getName(): String {
        return "World Gen"
    }
}