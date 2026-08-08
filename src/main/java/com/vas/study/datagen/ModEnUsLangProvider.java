package com.vas.study.datagen;

import com.vas.study.block.ModBlocks;
import com.vas.study.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModEnUsLangProvider extends FabricLanguageProvider {
    public ModEnUsLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder translationBuilder) {
        // Items
        translationBuilder.add(ModItems.STUDY_ITEM, "Study Item");
        translationBuilder.add(ModItems.OBSIDIAN_INGOT, "Obsidian Ingot");
        translationBuilder.add(ModItems.OBSIDIAN_APPLE, "Obsidian Apple");
        translationBuilder.add("itemGroup.study", "Study");
        translationBuilder.add(ModItems.REINFORCED_OBSIDIAN_APPLE, "Reinforced Obsidian Apple");
        translationBuilder.add(ModItems.OBSIDIAN_COAL, "Obsidian Coal");
        translationBuilder.add(ModItems.OBSIDIAN_ALLOY_INGOT, "Obsidian Alloy Ingot");
        translationBuilder.add(ModItems.ROSE_GOLD_INGOT, "Rose Gold Ingot");
        translationBuilder.add(ModItems.PROSPECTOR, "Prospector");
        // Armor
        translationBuilder.add(ModItems.OBSIDIAN_HELMET, "Obsidian Helmet");
        translationBuilder.add(ModItems.OBSIDIAN_CHESTPLATE, "Obsidian Chestplate");
        translationBuilder.add(ModItems.OBSIDIAN_LEGGINGS, "Obsidian Leggings");
        translationBuilder.add(ModItems.OBSIDIAN_BOOTS, "Obsidian Boots");
        // Blocks
        translationBuilder.add(ModBlocks.OBSIDIAN_BLOCK, "Obsidian Block");
        translationBuilder.add(ModBlocks.REINFORCED_OBSIDIAN, "Reinforced Obsidian");
        translationBuilder.add(ModBlocks.ALLOY_FURNACE, "Alloy Furnace");
        translationBuilder.add("container.alloy_furnace", "Alloy Furnace");
    }
}
