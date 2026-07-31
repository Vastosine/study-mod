package com.vas.study.item;

import com.vas.study.MyStudyMod;
import com.vas.study.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

public class ModCreativeModeTabs {
    public static final String MOD_ID = MyStudyMod.MOD_ID;
    public static final Logger LOGGER = MyStudyMod.LOGGER;
    public static final ResourceKey<CreativeModeTab> STUDY_TAB = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), MyStudyMod.withMODID("study")
    );

    public static final CreativeModeTab STUDY = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.OBSIDIAN_APPLE))
            .title(Component.translatable("itemGroup.study"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.STUDY_ITEM);
                output.accept(ModItems.OBSIDIAN_INGOT);
                output.accept(ModItems.OBSIDIAN_APPLE);
                output.accept(ModBlocks.OBSIDIAN_BLOCK);
            }).build();

    public static void onInitialize() {
        LOGGER.info("Creative Tab has been registered for " + MOD_ID);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, STUDY_TAB, STUDY);

    }
}
