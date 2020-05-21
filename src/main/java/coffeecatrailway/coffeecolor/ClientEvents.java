package coffeecatrailway.coffeecolor;

import coffeecatrailway.coffeecolor.client.entity.ColorMonsterRenderer;
import coffeecatrailway.coffeecolor.client.tileentity.ColorPortalTileEntityRenderer;
import coffeecatrailway.coffeecolor.common.block.IHasColor;
import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorEntities;
import coffeecatrailway.coffeecolor.registry.ColorTileEntities;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    public static void blockColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();
        blocks.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : DyeColor.PURPLE.getColorValue(),
                ColorBlocks.COLOR_GRASS_BLOCK.get(), ColorBlocks.COLOR_GRASS.get(), ColorBlocks.TALL_COLOR_GRASS.get());

        for (Block block : ForgeRegistries.BLOCKS.getValues())
            if (block instanceof IHasColor)
                blocks.register((state, world, pos, tintIndex) -> ((IHasColor) block).getColor().getColorValue(), block);
    }

    public static void itemColors() {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();

        itemColors.register((stack, tintIndex) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ColorBlocks.COLOR_GRASS_BLOCK.get(), ColorBlocks.COLOR_GRASS.get(), ColorBlocks.TALL_COLOR_GRASS.get());

        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof IHasColor).forEach(block -> itemColors.register(
                (stack, tintIndex) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex), block));

        ColorEntities.SPAWN_EGGS.forEach(egg -> itemColors.register((stack, tintIndex) -> egg.get().getColor(tintIndex), egg.get()));
    }

    public static void renderLayers() {
        RenderType cutoutMipped = RenderType.getCutoutMipped();

        RenderTypeLookup.setRenderLayer(ColorBlocks.COLOR_GRASS_BLOCK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ColorBlocks.COLOR_GRASS.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ColorBlocks.TALL_COLOR_GRASS.get(), cutoutMipped);

        for (Block block : ForgeRegistries.BLOCKS.getValues())
            if (block instanceof IHasColor)
                RenderTypeLookup.setRenderLayer(block, cutoutMipped);
    }

    public static void entityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(ColorEntities.COLOR_MONSTER.get(), ColorMonsterRenderer::new);
    }

    public static void tileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(ColorTileEntities.COLOR_PORTAL.get(), ColorPortalTileEntityRenderer::new);
    }
}
