package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;

import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class LightBlueBiome extends ColorBiome {

    public LightBlueBiome(Builder builder) {
        super(builder, DyeColor.LIGHT_BLUE);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.BLUE_ORCHID.getDefaultState());
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
