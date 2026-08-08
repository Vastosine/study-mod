package com.vas.study.item;

import com.vas.study.MyStudyMod;
import com.vas.study.item.custom.Prospector;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import org.slf4j.Logger;

import java.util.function.Function;


public class ModItems {
    public static final String MOD_ID = MyStudyMod.MOD_ID;
    public static final Logger LOGGER = MyStudyMod.LOGGER;
    public static final Item STUDY_ITEM = register("study_item");
    public static final Item OBSIDIAN_INGOT = register("obsidian_ingot");

    public static final Item OBSIDIAN_APPLE = register("obsidian_apple", new Item.Properties().food(ModFoods.OBSIDIAN_APPLE, ModConsumables.OBSIDIAN_APPLE));
    public static final Item REINFORCED_OBSIDIAN_APPLE = register("reinforced_obsidian_apple", new Item.Properties().food(ModFoods.REINFORCED_OBSIDIAN_APPLE, ModConsumables.REINFORCED_OBSIDIAN_APPLE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final Item OBSIDIAN_COAL = register("obsidian_coal");

    public static final Item PROSPECTOR = register("prospector", Prospector::new, new Item.Properties().durability(65536 * 256));

    // Obsidian Armor
    public static final Item OBSIDIAN_HELMET = registerArmor("obsidian_helmet", ArmorType.HELMET);
    public static final Item OBSIDIAN_CHESTPLATE = registerArmor("obsidian_chestplate", ArmorType.CHESTPLATE);
    public static final Item OBSIDIAN_LEGGINGS = registerArmor("obsidian_leggings", ArmorType.LEGGINGS);
    public static final Item OBSIDIAN_BOOTS = registerArmor("obsidian_boots", ArmorType.BOOTS);

    private static Item register(final String name, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, MyStudyMod.withMODID(name));
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item register(final String name, final Function<Item.Properties, Item> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    private static Item register(final String name) {
        return register(name, Item::new);
    }

    private static Item register(final String name, final Item.Properties properties) {
        return register(name, Item::new, properties);
    }

    private static Item registerArmor(final String name, final ArmorType type) {
        return register(name, properties -> {
            var material = ModArmorMaterials.OBSIDIAN;
            return new Item(properties
                    .durability(type.getDurability(material.durability()))
                    .attributes(material.createAttributes(type))
                    .enchantable(material.enchantmentValue())
                    .repairable(material.repairIngredient())
                    .component(DataComponents.EQUIPPABLE, Equippable.builder(type.getSlot())
                            .setEquipSound(material.equipSound())
                            .setAsset(material.assetId())
                            .setDamageOnHurt(true)
                            .setEquipOnInteract(true)
                            .setDispensable(true)
                            .build())
            );
        });
    }

    public static void onInitialize() {
        LOGGER.info("Items has been registered for " + MOD_ID);
        FuelValueEvents.BUILD.register(
                (builder, context) -> builder
                        .add(ModItems.OBSIDIAN_COAL, 2000)
        );
    }
}
