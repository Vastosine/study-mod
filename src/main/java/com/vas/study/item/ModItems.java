package com.vas.study.item;

import com.vas.study.StudyMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class ModItems {
    public static final Item STUDY_ITEM = registerItem("study_item");

    public static Item registerItem(final String name, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> resourceKey = ResourceKey.create(BuiltInRegistries.ITEM.key(), StudyMod.id(name));
        return Items.registerItem(resourceKey, function, properties);
    }

    public static Item registerItem(final String name) {
        return registerItem(name, Item::new, new Item.Properties());
    }

    public static void onInitialize() {
        StudyMod.LOGGER.info("Registering Mod Items for " + StudyMod.MOD_ID);
    }
}
