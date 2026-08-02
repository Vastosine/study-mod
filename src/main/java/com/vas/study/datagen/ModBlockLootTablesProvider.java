package com.vas.study.datagen;

import com.vas.study.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTablesProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTablesProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.OBSIDIAN_BLOCK);
        dropSelf(ModBlocks.REINFORCED_OBSIDIAN);
    }
}
