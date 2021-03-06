package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class MultiModelBlockComponent extends AbstractBlockComponent {

    private final int maxCount;
    private final IntegerProperty model;

    public MultiModelBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.model = IntegerProperty.create("model", 0, maxCount);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int nextModel = state.get(model) + 1;
        if (nextModel > maxCount) {
            nextModel = 0;
        }
        worldIn.setBlockState(pos, state.with(model, nextModel));
        return true;
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(model);
    }
}
