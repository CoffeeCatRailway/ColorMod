package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class BrownBiome extends ColorBiome {

    public BrownBiome(Builder builder) {
        super(builder, DyeColor.BROWN);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.BROWN_TREE.get().withConfiguration(ColorFeatures.getBrownTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.BROWN_GEM_ORE);
    }
}
