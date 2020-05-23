package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class GrayBiome extends ColorBiome {

    public GrayBiome(Builder builder) {
        super(builder, DyeColor.GRAY);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.GRAY_TREE.get().withConfiguration(ColorFeatures.getGrayTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.GRAY_GEM_ORE);
    }
}
