package com.vas.study.tag;

import com.vas.study.MyStudyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> OBSIDIAN_ITEMS = create("obsidian_items");
    /** Gold sources for alloying (ingot, ores, raw gold). */
    public static final TagKey<Item> GOLD_MATERIALS = create("gold_materials");
    /** Copper sources for alloying (ingot, ores, raw copper). */
    public static final TagKey<Item> COPPER_MATERIALS = create("copper_materials");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, MyStudyMod.withMODID(name));
    }
}
