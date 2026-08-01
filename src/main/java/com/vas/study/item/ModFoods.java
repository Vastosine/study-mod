package com.vas.study.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties OBSIDIAN_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().build();
    public static final FoodProperties REINFORCED_OBSIDIAN_APPLE = new FoodProperties.Builder().nutrition(6).saturationModifier(1.0F).alwaysEdible().build();
}
