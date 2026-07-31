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
}
