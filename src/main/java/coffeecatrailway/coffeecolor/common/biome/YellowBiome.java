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
public class YellowBiome extends ColorBiome {

    public YellowBiome(Builder builder) {
        super(builder, DyeColor.YELLOW);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.DANDELION.getDefaultState());
        flowers.add(Blocks.OXEYE_DAISY.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.YELLOW_TREE.get().withConfiguration(ColorFeatures.getYellowTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.YELLOW_GEM_ORE);
    }
}
