package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class LightGrayBiome extends ColorBiome {

    public LightGrayBiome(Builder builder) {
        super(builder, DyeColor.LIGHT_GRAY);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.LIGHT_GRAY_TREE.get().withConfiguration(ColorFeatures.getLightGrayTreeConfig()), ColorFeatures.GRAY_TREE.get().withConfiguration(ColorFeatures.getGrayTreeConfig()), 8);
    }
}
