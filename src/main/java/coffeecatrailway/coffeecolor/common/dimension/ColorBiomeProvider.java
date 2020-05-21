package coffeecatrailway.coffeecolor.common.dimension;

import coffeecatrailway.coffeecolor.common.biome.layer.ColorLayerUtil;
import coffeecatrailway.coffeecolor.registry.ColorBiomes;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;

import java.util.Set;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;

    public static final Set<Biome> BIOMES = ImmutableSet.of(
            ColorBiomes.WHITE_PLAINS.get(), ColorBiomes.WHITE_FOREST.get(), ColorBiomes.WHITE_FOREST_HILLS.get(),
            ColorBiomes.ORANGE_PLAINS.get(), ColorBiomes.ORANGE_FOREST.get(), ColorBiomes.ORANGE_FOREST_HILLS.get(),
            ColorBiomes.MAGENTA_PLAINS.get(), ColorBiomes.MAGENTA_FOREST.get(), ColorBiomes.MAGENTA_FOREST_HILLS.get(),
            ColorBiomes.LIGHT_BLUE_PLAINS.get(), ColorBiomes.LIGHT_BLUE_FOREST.get(), ColorBiomes.LIGHT_BLUE_FOREST_HILLS.get(),
            ColorBiomes.YELLOW_PLAINS.get(), ColorBiomes.YELLOW_FOREST.get(), ColorBiomes.YELLOW_FOREST_HILLS.get(),
            ColorBiomes.LIME_PLAINS.get(), ColorBiomes.LIME_FOREST.get(), ColorBiomes.LIME_FOREST_HILLS.get(),
            ColorBiomes.PINK_PLAINS.get(), ColorBiomes.PINK_FOREST.get(), ColorBiomes.PINK_FOREST_HILLS.get(),
            ColorBiomes.GRAY_PLAINS.get(), ColorBiomes.GRAY_FOREST.get(), ColorBiomes.GRAY_FOREST_HILLS.get(),
            ColorBiomes.LIGHT_GRAY_PLAINS.get(), ColorBiomes.LIGHT_GRAY_FOREST.get(), ColorBiomes.LIGHT_GRAY_FOREST_HILLS.get(),
            ColorBiomes.CYAN_PLAINS.get(), ColorBiomes.CYAN_FOREST.get(), ColorBiomes.CYAN_FOREST_HILLS.get(),
            ColorBiomes.PURPLE_PLAINS.get(), ColorBiomes.PURPLE_FOREST.get(), ColorBiomes.PURPLE_FOREST_HILLS.get(),
            ColorBiomes.BLUE_PLAINS.get(), ColorBiomes.BLUE_FOREST.get(), ColorBiomes.BLUE_FOREST_HILLS.get(),
            ColorBiomes.BROWN_PLAINS.get(), ColorBiomes.BROWN_FOREST.get(), ColorBiomes.BROWN_FOREST_HILLS.get(),
            ColorBiomes.GREEN_PLAINS.get(), ColorBiomes.GREEN_FOREST.get(), ColorBiomes.GREEN_FOREST_HILLS.get(),
            ColorBiomes.RED_PLAINS.get(), ColorBiomes.RED_FOREST.get(), ColorBiomes.RED_FOREST_HILLS.get(),
            ColorBiomes.BLACK_PLAINS.get(), ColorBiomes.BLACK_FOREST.get(), ColorBiomes.BLACK_FOREST_HILLS.get()
    );

    static {
        BIOMES_TO_SPAWN_IN.clear();
        BIOMES_TO_SPAWN_IN.addAll(BIOMES);
    }

    public ColorBiomeProvider(World world) {
        super(BIOMES);
        this.genBiomes = ColorLayerUtil.makeLayers(world.getWorldInfo().getSeed());
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.genBiomes.func_215738_a(x, z);
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty())
            for (Biome biome : BIOMES)
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        return this.topBlocksCache;
    }
}
