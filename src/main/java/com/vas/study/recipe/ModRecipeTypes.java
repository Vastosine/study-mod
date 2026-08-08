package com.vas.study.recipe;

import com.vas.study.MyStudyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            MyStudyMod.withMODID("alloy_furnace"),
            new RecipeType<AlloyFurnaceRecipe>() {
                @Override
                public String toString() {
                    return "alloy_furnace";
                }
            }
    );

    public static void onInitialize() {
        MyStudyMod.LOGGER.info("Recipe Types has been registered for " + MyStudyMod.MOD_ID);
    }
}
