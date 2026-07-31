package com.vas.study;

import com.vas.study.datagen.ModEnUsLangProvider;
import com.vas.study.datagen.ModModelsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MyStudyModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModEnUsLangProvider::new);
		pack.addProvider(ModModelsProvider::new);
	}
}
