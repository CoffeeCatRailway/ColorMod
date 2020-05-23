package coffeecatrailway.coffeecolor;

import coffeecatrailway.coffeecolor.common.biome.ColorBiome;
import coffeecatrailway.coffeecolor.integration.CuriosIntegration;
import coffeecatrailway.coffeecolor.network.PacketHandler;
import coffeecatrailway.coffeecolor.registrate.ColorLang;
import coffeecatrailway.coffeecolor.registrate.ColorModels;
import coffeecatrailway.coffeecolor.registrate.ColorRegistrate;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import coffeecatrailway.coffeecolor.registry.*;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

@Mod(ColorMod.MOD_ID)
@Mod.EventBusSubscriber(modid = ColorMod.MOD_ID)
public class ColorMod {

    public static final String MOD_ID = "coffeecolor";

    public static final Logger LOGGER = LogManager.getLogger();
    public static ColorRegistrate REGISTRATE;

    public static KeyBinding ACTIVE_AMULET = new KeyBinding("key." + MOD_ID + ".amulet", GLFW.GLFW_KEY_H, "key." + MOD_ID + ".category");

    public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ColorItems.COLOR_AMULET.get());
        }
    };

    public ColorMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);
        CuriosIntegration.init();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRATE = ColorRegistrate.create(MOD_ID, modEventBus);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, ColorTags.Blocks.INSTANCE);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ColorTags.Items.INSTANCE);
        REGISTRATE.addDataGenerator(ProviderType.BLOCKSTATE, ColorModels.INSTANCE);
        REGISTRATE.addDataGenerator(ProviderType.LANG, ColorLang.INSTANCE);

        ColorBlocks.load();
        ColorItems.load();
        ColorTileEntities.load();
        ColorEntities.load();
        ColorFeatures.FEATURES.register(modEventBus);
        ColorFeatures.DECORATORS.register(modEventBus);
        ColorMod.LOGGER.info("Register features & decorators");
        ColorBiomes.load();
        ColorBiomes.DIMENSIONS.register(modEventBus);
        ColorMod.LOGGER.info("Register dimensions");
        ColorRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ColorMod.LOGGER.info("Register recipe serializers");

        modEventBus.addListener(ColorEntities::registerPlacements);
    }

    public void setupClient(final FMLCommonSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::itemColors);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::blockColors);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::renderLayers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::entityRenderers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::tileEntityRenderers);

        ClientRegistry.registerKeyBinding(ACTIVE_AMULET);
        ColorMod.LOGGER.info("Client Event: Register key bind");
    }

    public void setupCommon(FMLCommonSetupEvent event) {
        ColorBiomes.addBiomeTypes();
        ColorBiomes.addBiomeFeatures();
        PacketHandler.init();
    }

    @SubscribeEvent
    public static void registerToManager(final RegisterDimensionsEvent event) {
        ColorBiomes.COLOR_DIMENSION_TYPE = DimensionManager.registerOrGetDimension(ColorMod.getLocation("color_dimension"), ColorBiomes.COLOR_DIMENSION.get(), null, true);
    }

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof SheepEntity) {
            Biome biome = event.getWorld().getBiome(entity.getPosition());
            if (biome instanceof ColorBiome)
                ((SheepEntity) entity).setFleeceColor(((ColorBiome) biome).getColor());
        }
    }

    public static ResourceLocation getLocation(String path) {
        return new ResourceLocation(ColorMod.MOD_ID, path);
    }
}
