package coffeecatrailway.coffeecolor.common.biome.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBiomeLayer implements IC0Transformer {

    private List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];
    private final OverworldGenSettings settings;

    public ColorBiomeLayer(WorldType worldType, OverworldGenSettings settings) {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            int idx = type.ordinal();

            if (biomes[idx] == null)
                biomes[idx] = new java.util.ArrayList<>();
        }

        if (worldType == WorldType.DEFAULT_1_1)
            this.settings = null;
        else
            this.settings = settings;
    }

    @Override
    public int apply(INoiseRandom context, int value) {
        if (this.settings != null && this.settings.getBiomeId() >= 0) {
            return this.settings.getBiomeId();
        } else {
            value = value & -3841;
            return value;
        }
    }
}
