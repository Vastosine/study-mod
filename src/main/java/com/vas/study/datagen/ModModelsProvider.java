package com.vas.study.datagen;

import com.vas.study.block.ModBlocks;
import com.vas.study.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.OBSIDIAN_BLOCK);
        blockModelGenerators.createTrivialBlock(ModBlocks.REINFORCED_OBSIDIAN, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.STUDY_ITEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_APPLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.itemModelOutput.accept(
                ModItems.REINFORCED_OBSIDIAN_APPLE,
                ItemModelUtils.plainModel(
                        ModelTemplates.FLAT_ITEM.create(
                                ModelLocationUtils.getModelLocation(ModItems.REINFORCED_OBSIDIAN_APPLE),
                                TextureMapping.layer0(ModItems.OBSIDIAN_APPLE),
                                itemModelGenerators.modelOutput
                        )
                )
        );
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_COAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PROSPECTOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.OBSIDIAN_BOOTS, ModelTemplates.FLAT_ITEM);
    }
}
