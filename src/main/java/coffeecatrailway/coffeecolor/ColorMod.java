package coffeecatrailway.coffeecolor;

import coffeecatrailway.coffeecolor.common.biome.ColorBiome;
import coffeecatrailway.coffeecolor.registrate.ColorModels;
import coffeecatrailway.coffeecolor.registrate.ColorRegistrate;
import coffeecatrailway.coffeecolor.registry.*;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ColorMod.MOD_ID)
@Mod.EventBusSubscriber(modid = ColorMod.MOD_ID)
public class ColorMod {

    public static final String MOD_ID = "coffeecolor";

    public static final Logger LOGGER = LogManager.getLogger();
    public static ColorRegistrate REGISTRATE;

    public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ColorItems.MAGIC_COLOR_DYE.get());
        }
    };

    public ColorMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRATE = ColorRegistrate.create(MOD_ID, modEventBus);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, ColorTags.Blocks.INSTANCE);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ColorTags.Items.INSTANCE);
        REGISTRATE.addDataGenerator(ProviderType.BLOCKSTATE, ColorModels.INSTANCE);

        ColorBlocks.load();
        ColorItems.load();
        ColorTileEntities.load();
        ColorEntities.load();
        ColorFeatures.FEATURES.register(modEventBus);
        ColorBiomes.load();
        ColorBiomes.DIMENSIONS.register(modEventBus);

        modEventBus.addListener(ColorEntities::registerPlacements);
    }

    public void setupClient(final FMLCommonSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::itemColors);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::blockColors);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::renderLayers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::entityRenderers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::tileEntityRenderers);
    }

    public void setupCommon(FMLCommonSetupEvent event) {
        ColorBiomes.addBiomeTypes();
        ColorBiomes.addBiomeFeatures();
    }

    @SubscribeEvent
    public static void registerToManager(final RegisterDimensionsEvent event) {
        ColorBiomes.COLOR_DIMENSION_TYPE = DimensionManager.registerOrGetDimension(ColorMod.getLocation("color_dimension"), ColorBiomes.COLOR_DIMENSION.get(), null, true);
    }

    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent spawnEvent) {
        Entity entity = spawnEvent.getEntity();
        if (entity instanceof SheepEntity) {
            Biome biome = spawnEvent.getWorld().getBiome(new BlockPos(spawnEvent.getX(), spawnEvent.getY(), spawnEvent.getZ()));
            if (biome instanceof ColorBiome)
                ((SheepEntity) entity).setFleeceColor(((ColorBiome) biome).getColor());
        }
    }

    public static ResourceLocation getLocation(String path) {
        return new ResourceLocation(ColorMod.MOD_ID, path);
    }
}
