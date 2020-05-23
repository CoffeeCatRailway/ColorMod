package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class LimeBiome extends ColorBiome {

    public LimeBiome(Builder builder) {
        super(builder, DyeColor.LIME);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.LIME_TREE.get().withConfiguration(ColorFeatures.getLimeTreeConfig()), ColorFeatures.GREEN_TREE.get().withConfiguration(ColorFeatures.getGreenTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.LIME_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.GREEN_GEM_ORE);
    }
}
