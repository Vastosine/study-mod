package com.vas.study.block.entity;

import com.vas.study.MyStudyMod;
import com.vas.study.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<AlloyFurnaceBlockEntity> ALLOY_FURNACE = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            MyStudyMod.withMODID("alloy_furnace"),
            FabricBlockEntityTypeBuilder.create(AlloyFurnaceBlockEntity::new, ModBlocks.ALLOY_FURNACE).build()
    );

    public static void onInitialize() {
        MyStudyMod.LOGGER.info("Block Entities has been registered for " + MyStudyMod.MOD_ID);
    }
}
