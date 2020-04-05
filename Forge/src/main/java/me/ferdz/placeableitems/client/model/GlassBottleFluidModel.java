package me.ferdz.placeableitems.client.model;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.client.model.complex.ModelRenderDefinition;
import me.ferdz.placeableitems.client.model.complex.ModelRenderElement;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * An implementation of {@link FluidModel} for the {@link PlaceableItemsBlockRegistry#GLASS_BOTTLE}.
 *
 * @author Parker Hawke - Choco
 */
public class GlassBottleFluidModel extends FluidModel {

    private static final Vector3d ORIGIN = new Vector3d(8.0D, 1.0D, 8.0D);
    private static final Vector3d UP_ORIGIN = new Vector3d(8.0D, 5.0D, 8.0D);

    private static final ModelRenderDefinition MODEL_DEFINITION = ModelRenderDefinition.withElements(
            ModelRenderElement.fromAABB(-2.5 / 16.0, 0.0D, -2.5 / 16.0, 2.5 / 16.0, 4 / 16.0, 2.5 / 16.0)
    );

    @Override
    public ModelRenderDefinition calculateModelDefinition(BlockState state) {
        return MODEL_DEFINITION.rotateAroundY(Math.toRadians((-state.get(PlaceableItemsBlock.ROTATION) * 360F / 16.0F)));
    }

    @Override
    public Vector3d getOrigin(BlockState state) {
        return state.get(BiPositionBlockComponent.UP) ? UP_ORIGIN : ORIGIN;
    }

    @Override
    public boolean shouldRender(BlockState state, Direction direction) {
        return direction != Direction.DOWN;
    }

    @Override
    public int getLight(World world, BlockPos pos, Direction direction) {
        if (direction == Direction.UP) {
            int light = super.getLight(world, pos, direction);
            light |= super.getLight(world, pos, Direction.NORTH);
            light |= super.getLight(world, pos, Direction.SOUTH);
            light |= super.getLight(world, pos, Direction.EAST);
            light |= super.getLight(world, pos, Direction.WEST);

            return light;
        }

        return super.getLight(world, pos, direction);
    }

}
