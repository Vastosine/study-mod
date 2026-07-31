package com.vas.study.item;

import com.vas.study.MyStudyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import java.util.function.Function;


public class ModItems {
    public static final String MOD_ID = MyStudyMod.MOD_ID;
    public static final Logger LOGGER = MyStudyMod.LOGGER;
    public static final Item STUDY_ITEM = regitsterItem("study_item");
    public static final Item OBSIDIAN_INGOT = regitsterItem("obsidian_ingot");
    public static final Item OBSIDIAN_APPLE = regitsterItem("obsidian_apple", new Item.Properties().food(ModFoods.OBSIDIAN_APPLE, ModConsumables.OBSIDIAN_APPLE));

    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, MyStudyMod.withMODID(name));
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory) {
        return registerItem(name, itemFactory, new Item.Properties());
    }

    private static Item regitsterItem(final String name) {
        return registerItem(name, Item::new);
    }

    private static Item regitsterItem(final String name, final Item.Properties properties) {
        return registerItem(name, Item::new, properties);
    }

    public static void onInitialize() {
        LOGGER.info("Items has been registered for " + MOD_ID);
//        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
//                .register(fabricCreativeModeTabOutput -> {
//                    fabricCreativeModeTabOutput.accept(STUDY_ITEM);
//                });
//        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
//                .register(fabricCreativeModeTabOutput -> {
//                    fabricCreativeModeTabOutput.accept(OBSIDIAN_APPLE);
//                });
    }
}
