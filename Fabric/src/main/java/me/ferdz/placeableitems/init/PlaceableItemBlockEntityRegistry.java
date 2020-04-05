package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.blockentities.FluidHolderBlockEntity;
import me.ferdz.placeableitems.blockentities.PlaceableItemBlockEntity;
import me.ferdz.placeableitems.blockentities.StackHolderBlockEntity;
import me.ferdz.placeableitems.blockentities.renderers.FluidHolderBlockEntityRenderer;
import me.ferdz.placeableitems.blockentities.renderers.PlaceableItemBlockEntityRenderer;
import me.ferdz.placeableitems.client.model.GlassBottleFluidModel;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class PlaceableItemBlockEntityRegistry {

    public static BlockEntityType<StackHolderBlockEntity> WRITABLE_BOOK_TILE_ENTITY;
    public static BlockEntityType<FluidHolderBlockEntity> FLUID_HOLDER_BLOCK_ENTITY;
    public static BlockEntityType<PlaceableItemBlockEntity> PLACEABLE_ITEM_BLOCK_ENTITY;

    public static void registerTE() {
        WRITABLE_BOOK_TILE_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY,
                new Identifier(PlaceableItems.MODID, "writable_book_block"),
                BlockEntityType.Builder
                        .create(StackHolderBlockEntity::new, PlaceableItemsBlockRegistry.stackHoldingBlocks.get())
                        .build(null));
        FLUID_HOLDER_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY,
                new Identifier(PlaceableItems.MODID, "fluid_holder_block_entity"),
                BlockEntityType.Builder
                        .create(FluidHolderBlockEntity::new, PlaceableItemsBlockRegistry.fluidHoldingBlocks.get())
                        .build(null));
        PLACEABLE_ITEM_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY,
                new Identifier(PlaceableItems.MODID, "placeableitemblockentity"),
                BlockEntityType.Builder
                        .create(PlaceableItemBlockEntity::new, PlaceableItemsBlockRegistry.blocks.get())
                        .build(null));
    }

    @Environment(EnvType.CLIENT)
    public static void registerTERenderer() {
        BlockEntityRendererRegistry.INSTANCE.register(
                PlaceableItemBlockEntity.class,
                new PlaceableItemBlockEntityRenderer<PlaceableItemBlockEntity>());
        BlockEntityRendererRegistry.INSTANCE.register(
                StackHolderBlockEntity.class,
                new PlaceableItemBlockEntityRenderer<StackHolderBlockEntity>());
        BlockEntityRendererRegistry.INSTANCE.register(
                FluidHolderBlockEntity.class,
                new FluidHolderBlockEntityRenderer()
                    .bind(PlaceableItemsBlockRegistry.GLASS_BOTTLE, new GlassBottleFluidModel())
                );
    }

}