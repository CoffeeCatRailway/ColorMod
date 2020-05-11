package coffeecatrailway.coffeecolor.common.biome.layer;

import coffeecatrailway.coffeecolor.common.dimension.ColorBiomeProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public enum ColorLayer implements IAreaTransformer0 {
    INSTANCE;

    @Override
    public int apply(INoiseRandom rand, int p_215735_2_, int p_215735_3_) {
        return Registry.BIOME.getId(ColorBiomeProvider.BIOMES.toArray(new Biome[]{})[rand.random(ColorBiomeProvider.BIOMES.size())]);
    }
}
