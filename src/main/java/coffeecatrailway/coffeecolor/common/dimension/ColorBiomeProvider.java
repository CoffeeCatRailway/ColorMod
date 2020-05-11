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
            ColorBiomes.WHITE_PLAINS.get(),
            ColorBiomes.ORANGE_PLAINS.get(),
            ColorBiomes.MAGENTA_PLAINS.get(),
            ColorBiomes.LIGHT_BLUE_PLAINS.get(),
            ColorBiomes.YELLOW_PLAINS.get(),
            ColorBiomes.LIME_PLAINS.get(),
            ColorBiomes.PINK_PLAINS.get(),
            ColorBiomes.GRAY_PLAINS.get(),
            ColorBiomes.LIGHT_GRAY_PLAINS.get(),
            ColorBiomes.CYAN_PLAINS.get(),
            ColorBiomes.PURPLE_PLAINS.get(),
            ColorBiomes.BLUE_PLAINS.get(),
            ColorBiomes.BROWN_PLAINS.get(),
            ColorBiomes.GREEN_PLAINS.get(),
            ColorBiomes.RED_PLAINS.get(),
            ColorBiomes.BLACK_PLAINS.get()
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
