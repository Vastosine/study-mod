package com.vas.study.menu;

import com.vas.study.MyStudyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<AlloyFurnaceMenu> ALLOY_FURNACE = Registry.register(
            BuiltInRegistries.MENU,
            MyStudyMod.withMODID("alloy_furnace"),
            new MenuType<>(AlloyFurnaceMenu::new, FeatureFlags.VANILLA_SET)
    );

    public static void onInitialize() {
        MyStudyMod.LOGGER.info("Menu Types has been registered for " + MyStudyMod.MOD_ID);
    }
}
