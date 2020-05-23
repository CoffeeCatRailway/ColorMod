package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class LightBlueBiome extends ColorBiome {

    public LightBlueBiome(Builder builder) {
        super(builder, DyeColor.LIGHT_BLUE);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.LIGHT_BLUE_TREE.get().withConfiguration(ColorFeatures.getLightBlueTreeConfig()), ColorFeatures.CYAN_TREE.get().withConfiguration(ColorFeatures.getCyanTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.LIGHT_BLUE_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.CYAN_GEM_ORE);
    }
}
