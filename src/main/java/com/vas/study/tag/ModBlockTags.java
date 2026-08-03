package com.vas.study.tag;

import com.vas.study.MyStudyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> ORE_TO_PROSPECT = create("ore_to_prospect");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, MyStudyMod.withMODID(name));
    }
}
