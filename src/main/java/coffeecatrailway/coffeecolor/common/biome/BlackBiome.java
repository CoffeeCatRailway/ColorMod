package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class BlackBiome extends ColorBiome {

    public BlackBiome(Builder builder) {
        super(builder, DyeColor.BLACK);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.BLUE_TREE.get().withConfiguration(ColorFeatures.getBlackTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.BLACK_GEM_ORE);
    }
}
