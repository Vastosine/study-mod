package com.vas.study;

import com.vas.study.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class StudyModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
//		pack.addProvider(ModRecipesProvider::new);
//		pack.addProvider(ModLootTableProvider::new);
//		pack.addProvider(ModBlockTagsProvider::new);
//		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModEnUsProvider::new);
		pack.addProvider(ModModelsProvider::new);
	}
}
