package com.vas.study.item;

import com.vas.study.StudyMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final  ResourceKey<CreativeModeTab> key =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, StudyMod.id("study_tab"));
    public static final CreativeModeTab STUDY_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.STUDY_ITEM))
            .title(Component.translatable("itemGroup.study"))
            .displayItems((context, output) -> {
                output.accept(ModItems.STUDY_ITEM);
                output.accept(ModItems.TEST_ITEM);
            })
            .build();

    public static void onInitialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, STUDY_TAB
        );
    }
}
