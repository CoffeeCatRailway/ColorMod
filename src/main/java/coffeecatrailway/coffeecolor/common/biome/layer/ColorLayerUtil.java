package coffeecatrailway.coffeecolor.common.biome.layer;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.registry.ColorBiomes;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLModIdMappingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
@Mod.EventBusSubscriber(modid = ColorMod.MOD_ID)
public class ColorLayerUtil {

    private static final List<LazyInt> CACHE = new ArrayList<>();

    protected static final LazyInt RIVER = lazyId(ColorBiomes.COLOR_RIVER);

    static LazyInt lazyId(RegistryEntry<? extends Biome> biome) {
        LazyInt lazyInt = new LazyInt(biome.lazyMap(Registry.BIOME::getId));
        CACHE.add(lazyInt);
        return lazyInt;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory) {
        IAreaFactory<T> biomes = ColorLayer.INSTANCE.apply(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);

        biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        IAreaFactory<T> riverLayer = ColorRiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        biomes = ColorRiverMixLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed) {
        IAreaFactory<LazyArea> areaFactory = makeLayers((seedModifier) -> new LazyAreaLayerContext(25, seed, seedModifier));
        return new Layer(areaFactory);
    }

    @SubscribeEvent
    public static void onModIdMapped(FMLModIdMappingEvent event) {
        CACHE.forEach(LazyInt::invalidate);
    }
}
