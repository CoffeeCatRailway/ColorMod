package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class OrangeBiome extends ColorBiome {

    public OrangeBiome(Builder builder) {
        super(builder, DyeColor.ORANGE);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.ORANGE_TREE.get().withConfiguration(ColorFeatures.getOrangeTreeConfig()), ColorFeatures.RED_TREE.get().withConfiguration(ColorFeatures.getRedTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.ORANGE_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.RED_GEM_ORE);
    }
}
