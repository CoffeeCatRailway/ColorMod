package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class WhiteBiome extends ColorBiome {

    public WhiteBiome(Builder builder) {
        super(builder, DyeColor.WHITE);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.WHITE_TREE.get().withConfiguration(ColorFeatures.getWhiteTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.WHITE_GEM_ORE);
    }
}
