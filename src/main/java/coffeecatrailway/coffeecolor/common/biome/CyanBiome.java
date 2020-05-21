package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class CyanBiome extends ColorBiome {

    public CyanBiome(Builder builder) {
        super(builder, DyeColor.CYAN);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.CYAN_TREE.get().withConfiguration(ColorFeatures.getCyanTreeConfig()), ColorFeatures.BLUE_TREE.get().withConfiguration(ColorFeatures.getBlueTreeConfig()), 8);
    }
}
