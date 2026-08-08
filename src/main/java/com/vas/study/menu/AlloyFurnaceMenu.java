package com.vas.study.menu;

import com.vas.study.block.entity.AlloyFurnaceBlockEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Menu for the Alloy Furnace. Slot layout:
 * 0/1/2 = ingredients, 3 = fuel, 4 = output, 5..40 = player inventory (addStandardInventorySlots).
 */
public class AlloyFurnaceMenu extends AbstractContainerMenu {
    public static final int SLOT_COUNT = 5;
    public static final int DATA_COUNT = 4;
    private static final int INV_SLOT_START = 5;
    private static final int INV_SLOT_END = 32;
    private static final int USE_ROW_SLOT_START = 32;
    private static final int USE_ROW_SLOT_END = 41;

    private final Container container;
    private final ContainerData data;
    protected final Level level;

    public AlloyFurnaceMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public AlloyFurnaceMenu(final int containerId, final Inventory inventory, final Container container, final ContainerData data) {
        super(ModMenuTypes.ALLOY_FURNACE, containerId);
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
        this.addSlot(new Slot(container, 0, 30, 17));
        this.addSlot(new Slot(container, 1, 56, 17));
        this.addSlot(new Slot(container, 2, 82, 17));
        this.addSlot(new Slot(container, AlloyFurnaceBlockEntity.SLOT_FUEL, 56, 53));
        this.addSlot(new Slot(container, AlloyFurnaceBlockEntity.SLOT_OUTPUT, 116, 35));
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    @Override
    public boolean stillValid(final Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(final Player player, final int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == AlloyFurnaceBlockEntity.SLOT_OUTPUT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex < SLOT_COUNT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.moveItemStackTo(stack, 0, AlloyFurnaceBlockEntity.SLOT_FUEL, false)) {
                // moved into an ingredient slot
            } else if (this.isFuel(stack)) {
                if (!this.moveItemStackTo(stack, AlloyFurnaceBlockEntity.SLOT_FUEL, AlloyFurnaceBlockEntity.SLOT_FUEL + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= INV_SLOT_START && slotIndex < INV_SLOT_END) {
                if (!this.moveItemStackTo(stack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= USE_ROW_SLOT_START && slotIndex < USE_ROW_SLOT_END && !this.moveItemStackTo(stack, INV_SLOT_START, INV_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return clicked;
    }

    public boolean isFuel(final ItemStack itemStack) {
        return this.level.fuelValues().isFuel(itemStack);
    }

    public float getBurnProgress() {
        int current = this.data.get(AlloyFurnaceBlockEntity.DATA_COOKING_PROGRESS);
        int total = this.data.get(AlloyFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME);
        return total != 0 && current != 0 ? Mth.clamp((float) current / total, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        int litDuration = this.data.get(AlloyFurnaceBlockEntity.DATA_LIT_DURATION);
        if (litDuration == 0) {
            litDuration = 200;
        }
        return Mth.clamp((float) this.data.get(AlloyFurnaceBlockEntity.DATA_LIT_TIME) / litDuration, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(AlloyFurnaceBlockEntity.DATA_LIT_TIME) > 0;
    }
}
