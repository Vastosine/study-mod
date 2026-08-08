package com.vas.study.item;

import com.vas.study.MyStudyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;
import java.util.Map;

public class ModArmorMaterials {
    public static final ResourceKey<EquipmentAsset> OBSIDIAN_EQUIPMENT_ASSET =
            ResourceKey.create(EquipmentAssets.ROOT_ID, MyStudyMod.withMODID("obsidian"));

    public static final TagKey<Item> REPAIRS_OBSIDIAN_ARMOR = TagKey.create(Registries.ITEM, MyStudyMod.withMODID("repairs_obsidian_armor"));

    public static final ArmorMaterial OBSIDIAN = new ArmorMaterial(
            25,                                                      // durability multiplier
            makeDefense(3, 5, 7, 3, 0),     // boots, leggings, chestplate, helmet, body
            12,                                                      // enchantment value
            SoundEvents.ARMOR_EQUIP_IRON,                            // equip sound
            1.0F,                                                    // toughness
            0.0F,                                                    // knockback resistance
            REPAIRS_OBSIDIAN_ARMOR,                                  // repair ingredient tag
            OBSIDIAN_EQUIPMENT_ASSET                                 // equipment asset
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        EnumMap<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        map.put(ArmorType.BODY, body);
        return map;
    }
}
