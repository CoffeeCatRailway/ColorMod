package coffeecatrailway.coffeecolor;

import coffeecatrailway.coffeecolor.client.entity.ColorMonsterRenderer;
import coffeecatrailway.coffeecolor.client.tileentity.ColorPortalTileEntityRenderer;
import coffeecatrailway.coffeecolor.common.IHasColor;
import coffeecatrailway.coffeecolor.curios.CuriosIntegration;
import coffeecatrailway.coffeecolor.network.PacketHandler;
import coffeecatrailway.coffeecolor.network.UseColorAmuletMessage;
import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorEntities;
import coffeecatrailway.coffeecolor.registry.ColorTileEntities;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ColorMod.MOD_ID)
public class ClientEvents {

    public static void blockColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();
        blocks.register((state, world, pos, tintindex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : DyeColor.PURPLE.getColorValue(),
                ColorBlocks.COLOR_GRASS_BLOCK.get(), ColorBlocks.COLOR_GRASS.get(), ColorBlocks.TALL_COLOR_GRASS.get());

        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof IHasColor).forEach(block ->
                blocks.register((state, world, pos, tintindex) -> ((IHasColor) block).getColor(new ItemStack(block), tintindex), block));

        ColorMod.LOGGER.info("Client Event: Block colors");
    }

    public static void itemColors() {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();

        itemColors.register((stack, tintindex) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintindex),
                ColorBlocks.COLOR_GRASS_BLOCK.get(), ColorBlocks.COLOR_GRASS.get(), ColorBlocks.TALL_COLOR_GRASS.get());

        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof IHasColor).forEach(block -> itemColors.register(
                (stack, tintindex) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintindex), block));
        ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof IHasColor).forEach(item -> itemColors.register(((IHasColor) item)::getColor, item));

        ColorEntities.SPAWN_EGGS.forEach(egg -> itemColors.register((stack, tintindex) -> egg.get().getColor(tintindex), egg.get()));

        ColorMod.LOGGER.info("Client Event: Item colors");
    }

    public static void renderLayers() {
        RenderType cutoutMipped = RenderType.getCutoutMipped();

        RenderTypeLookup.setRenderLayer(ColorBlocks.COLOR_GRASS_BLOCK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ColorBlocks.COLOR_GRASS.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ColorBlocks.TALL_COLOR_GRASS.get(), cutoutMipped);

        for (Block block : ForgeRegistries.BLOCKS.getValues())
            if (block instanceof IHasColor)
                RenderTypeLookup.setRenderLayer(block, cutoutMipped);

        ColorMod.LOGGER.info("Client Event: Block layers");
    }

    public static void entityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(ColorEntities.COLOR_MONSTER.get(), ColorMonsterRenderer::new);
    }

    public static void tileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(ColorTileEntities.COLOR_PORTAL.get(), ColorPortalTileEntityRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void keyPressed(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && ColorMod.ACTIVE_AMULET.isPressed()) {
            ColorMod.LOGGER.debug("Amulet keybind pressed!");
            ClientPlayerEntity player = minecraft.player;
            if (!CuriosIntegration.getAmuletStack(player).isEmpty())
                PacketHandler.INSTANCE.sendToServer(new UseColorAmuletMessage());
        }
    }
}
