package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class BlueBiome extends ColorBiome {

    public BlueBiome(Builder builder) {
        super(builder, DyeColor.BLUE);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.BLUE_TREE.get().withConfiguration(ColorFeatures.getBlueTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.BLUE_GEM_ORE);
    }
}
