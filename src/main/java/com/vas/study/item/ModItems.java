package com.vas.study.item;

import com.vas.study.StudyMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItems {
    public static final Item STUDY_ITEM = registerItem("study_item");

    public static Item registerItem(final String name, Item item) {
        ResourceKey<Item> resourceKey = ResourceKey.create(BuiltInRegistries.ITEM.key(), StudyMod.id(name));
        return Items.registerItem(resourceKey, item);
    }

    public static Item registerItem(final String name) {
        return registerItem(name, new Item(new Item.Properties()));
    }

    public static void onInitialize() {
        StudyMod.LOGGER.info("Registering Mod Items for " + StudyMod.MOD_ID);
    }
}
