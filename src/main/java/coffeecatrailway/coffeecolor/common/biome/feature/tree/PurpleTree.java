package coffeecatrailway.coffeecolor.common.biome.feature.tree;

import coffeecatrailway.coffeecolor.common.biome.feature.ColorTreeFeatureConfig;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public class PurpleTree extends ColorTree {

    @Override
    public ConfiguredFeature<ColorTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return ColorFeatures.PURPLE_TREE.get().withConfiguration(ColorFeatures.getPurpleTreeConfig());
    }
}
