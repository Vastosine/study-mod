package com.vas.study;

import com.vas.study.client.screen.AlloyFurnaceScreen;
import com.vas.study.menu.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class MyStudyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.ALLOY_FURNACE, AlloyFurnaceScreen::new);
    }
}
