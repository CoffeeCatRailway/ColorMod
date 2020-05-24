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
public class GrayBiome extends ColorBiome {

    public GrayBiome(Builder builder) {
        super(builder, DyeColor.GRAY);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.AZURE_BLUET.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.GRAY_TREE.get().withConfiguration(ColorFeatures.getGrayTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.GRAY_GEM_ORE);
    }
}
