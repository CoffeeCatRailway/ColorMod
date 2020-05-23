package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class YellowBiome extends ColorBiome {

    public YellowBiome(Builder builder) {
        super(builder, DyeColor.YELLOW);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.YELLOW_TREE.get().withConfiguration(ColorFeatures.getYellowTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.YELLOW_GEM_ORE);
    }
}
