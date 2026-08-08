package com.vas.study.block;

import com.mojang.serialization.MapCodec;
import com.vas.study.block.entity.AlloyFurnaceBlockEntity;
import com.vas.study.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class AlloyFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<AlloyFurnaceBlock> CODEC = simpleCodec(AlloyFurnaceBlock::new);

    public AlloyFurnaceBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<AlloyFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        return new AlloyFurnaceBlockEntity(worldPosition, blockState);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        return level instanceof ServerLevel serverLevel
                ? createTickerHelper(type, ModBlockEntities.ALLOY_FURNACE, (innerLevel, pos, state, entity) -> AlloyFurnaceBlockEntity.serverTick(serverLevel, pos, state, entity))
                : null;
    }

    @Override
    protected void openContainer(final Level level, final BlockPos pos, final Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AlloyFurnaceBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
        }
    }
}
