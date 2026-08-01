package com.vas.study.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {
    public static final Consumable OBSIDIAN_APPLE = Consumables.defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 200),
                                    new MobEffectInstance(MobEffects.SLOWNESS, 400)
                            )
                    )
            )
            .consumeSeconds(3.2f)
            .build();
    public static final Consumable REINFORCED_OBSIDIAN_APPLE = Consumables.defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 2400, 1),
                                    new MobEffectInstance(MobEffects.SLOWNESS, 600, 2),
                                    new MobEffectInstance(MobEffects.POISON, 120, 1)
                            )
                    )
            )
            .consumeSeconds(6.4f)
            .build();
}
