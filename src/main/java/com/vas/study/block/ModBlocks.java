package com.vas.study.block;

import com.vas.study.MyStudyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.Logger;

import java.util.function.Function;

public class ModBlocks {
    public static final String MOD_ID = MyStudyMod.MOD_ID;
    public static final Logger LOGGER = MyStudyMod.LOGGER;
    public static final Block OBSIDIAN_BLOCK = register("obsidian_block", BlockBehaviour.Properties.of().strength(10.0f, 1200.0f).requiresCorrectToolForDrops());

    public static Block register(final String name, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, MyStudyMod.withMODID(name));
        Block block = factory.apply(properties.setId(key));
        if (shouldRegisterItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    public static Block register(final String name, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        return register(name, factory, properties, true);
    }

    public static Block register(final String name, final BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        return register(name, Block::new, properties, shouldRegisterItem);
    }

    public static Block register(final String name, final BlockBehaviour.Properties properties) {
        return register(name, Block::new, properties, true);
    }

    public static void registerBlockItem(final String name, Block block) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, MyStudyMod.withMODID(name));
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, blockItem);
    }

    public static void onInitialize() {
        LOGGER.info("Blocks has been registered for " + MOD_ID);
    }
}
