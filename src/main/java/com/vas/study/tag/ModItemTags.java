package com.vas.study.tag;

import com.vas.study.MyStudyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> OBSIDIAN_ITEMS = create("obsidian_items");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, MyStudyMod.withMODID(name));
    }
}
