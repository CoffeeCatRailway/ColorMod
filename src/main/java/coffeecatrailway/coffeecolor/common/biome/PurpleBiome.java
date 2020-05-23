package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class PurpleBiome extends ColorBiome {

    public PurpleBiome(Builder builder) {
        super(builder, DyeColor.PURPLE);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.PURPLE_TREE.get().withConfiguration(ColorFeatures.getPurpleTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.PURPLE_GEM_ORE);
    }
}
