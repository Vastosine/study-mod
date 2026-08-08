package com.vas.study.datagen;

import com.vas.study.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        valueLookupBuilder(ModItemTags.OBSIDIAN_ITEMS)
                .add(Items.OBSIDIAN)
                .add(Items.CRYING_OBSIDIAN);
        valueLookupBuilder(ModItemTags.GOLD_MATERIALS)
                .add(Items.GOLD_INGOT)
                .add(Items.GOLD_ORE)
                .add(Items.DEEPSLATE_GOLD_ORE)
                .add(Items.NETHER_GOLD_ORE)
                .add(Items.RAW_GOLD);
        valueLookupBuilder(ModItemTags.COPPER_MATERIALS)
                .add(Items.COPPER_INGOT)
                .add(Items.COPPER_ORE)
                .add(Items.DEEPSLATE_COPPER_ORE)
                .add(Items.RAW_COPPER);
    }
}
