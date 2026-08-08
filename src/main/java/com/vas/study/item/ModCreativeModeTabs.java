package com.vas.study.item;

import com.vas.study.MyStudyMod;
import com.vas.study.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
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
                output.accept(ModItems.REINFORCED_OBSIDIAN_APPLE);
                output.accept(ModItems.OBSIDIAN_COAL);
                output.accept(ModItems.PROSPECTOR);

                // Obsidian Armor — comes with Fire Protection I (vanilla-style, resolved at display time)
                var enchantmentLookup = parameters.holders().lookupOrThrow(Registries.ENCHANTMENT);
                var fireProtection = enchantmentLookup.getOrThrow(Enchantments.FIRE_PROTECTION);
                ItemStack helmet = new ItemStack(ModItems.OBSIDIAN_HELMET);
                helmet.enchant(fireProtection, 2);
                output.accept(helmet);
                ItemStack chestplate = new ItemStack(ModItems.OBSIDIAN_CHESTPLATE);
                chestplate.enchant(fireProtection, 2);
                output.accept(chestplate);
                ItemStack leggings = new ItemStack(ModItems.OBSIDIAN_LEGGINGS);
                leggings.enchant(fireProtection, 2);
                output.accept(leggings);
                ItemStack boots = new ItemStack(ModItems.OBSIDIAN_BOOTS);
                boots.enchant(fireProtection, 2);
                output.accept(boots);

                output.accept(ModBlocks.OBSIDIAN_BLOCK);
                output.accept(ModBlocks.REINFORCED_OBSIDIAN);
            }).build();

    public static void onInitialize() {
        LOGGER.info("Creative Tab has been registered for " + MOD_ID);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, STUDY_TAB, STUDY);

    }
}
