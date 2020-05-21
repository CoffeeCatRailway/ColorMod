package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class RedBiome extends ColorBiome {

    public RedBiome(Builder builder) {
        super(builder, DyeColor.RED);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.RED_TREE.get().withConfiguration(ColorFeatures.getRedTreeConfig()), 8);
    }
}
