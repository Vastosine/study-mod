package com.vas.study.recipe;

import com.vas.study.MyStudyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {
    public static final RecipeSerializer<AlloyFurnaceRecipe> ALLOY_FURNACE = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            MyStudyMod.withMODID("alloy_furnace"),
            new RecipeSerializer<>(AlloyFurnaceRecipe.MAP_CODEC, AlloyFurnaceRecipe.STREAM_CODEC)
    );

    public static void onInitialize() {
        MyStudyMod.LOGGER.info("Recipe Serializers has been registered for " + MyStudyMod.MOD_ID);
    }
}
