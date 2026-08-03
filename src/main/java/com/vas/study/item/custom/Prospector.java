package com.vas.study.item.custom;

import com.vas.study.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.Queue;

public class Prospector extends Item {
    public Prospector(Properties properties) {
        super(properties);
    }

    public static final int PROSPECT_RANGE = 8;

    void sendMessage(UseOnContext context, final String message) {
        Player player = context.getPlayer();
        if (player != null) {
            player.sendSystemMessage(Component.literal(message));
        }
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        if (!level.isClientSide()) {
            sendMessage(context, "Prospecting for ores...");
            Queue<BlockPos> queue = new java.util.LinkedList<>();
            queue.offer(blockPos);
//            int dis = 0;
            while (!queue.isEmpty()) {
                BlockPos pos = queue.poll();
//                queue.poll();
                int distance = blockPos.distManhattan(pos);
//                if (distance < dis) continue;
//                if (distance > dis) dis = distance;
                if (distance > PROSPECT_RANGE) break;
                if (player != null) {
                    context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                if (isCorrectBlock(level.getBlockState(pos))) {
                    sendMessage(context, "Found " + level.getBlockState(pos).getBlock().getName().getString() + " with distance of " + distance);
                    return super.useOn(context);
                }
                if (pos.north().distManhattan(blockPos) >= distance) queue.offer(pos.north());
                if (pos.south().distManhattan(blockPos) >= distance) queue.offer(pos.south());
                if (pos.east().distManhattan(blockPos) >= distance) queue.offer(pos.east());
                if (pos.west().distManhattan(blockPos) >= distance) queue.offer(pos.west());
                if (pos.above().distManhattan(blockPos) >= distance) queue.offer(pos.above());
                if (pos.below().distManhattan(blockPos) >= distance) queue.offer(pos.below());
            }
            sendMessage(context, "No ores found within range of " + PROSPECT_RANGE);
        }
        return super.useOn(context);
    }

    boolean isCorrectBlock(final BlockState blockState) {
        return blockState.is(ModBlockTags.ORE_TO_PROSPECT);
    }
}
