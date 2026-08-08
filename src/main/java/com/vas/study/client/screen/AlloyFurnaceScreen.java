package com.vas.study.client.screen;

import com.vas.study.MyStudyMod;
import com.vas.study.menu.AlloyFurnaceMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {
    private static final Identifier TEXTURE = MyStudyMod.withMODID("textures/gui/container/alloy_furnace.png");
    private static final Identifier LIT_PROGRESS_SPRITE = MyStudyMod.withMODID("container/alloy_furnace/lit_progress");
    private static final Identifier BURN_PROGRESS_SPRITE = MyStudyMod.withMODID("container/alloy_furnace/burn_progress");

    public AlloyFurnaceScreen(final AlloyFurnaceMenu menu, final Inventory inventory, final Component title) {
        super(menu, inventory, title, 176, 166);
    }

    @Override
    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = this.leftPos;
        int yo = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        if (this.menu.isLit()) {
            int litSpriteHeight = 14;
            int litProgressHeight = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - litProgressHeight, xo + 56, yo + 36 + 14 - litProgressHeight, 14, litProgressHeight
            );
        }

        int burnSpriteWidth = 24;
        int burnProgressWidth = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_SPRITE, 24, 16, 0, 0, xo + 79, yo + 34, burnProgressWidth, 16);
    }
}
